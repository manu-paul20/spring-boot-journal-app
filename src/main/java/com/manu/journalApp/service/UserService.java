package com.manu.journalApp.service;

import com.manu.journalApp.entity.User;
import com.manu.journalApp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getAll(){
        return userRepo.findAll();
    }

    /**
     * Saves a new user and encodes their password.
     * @param user user with userName and password in plaintext!!
     * */
    public void saveNewUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of("USER"));
        userRepo.save(user);
    }

    public void saveNewAdmin(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of("USER","ADMIN"));
        userRepo.save(user);
    }

    /**
     * Updates the existing user. Use it for updating other fields except the userName and password
     * @param user user object with updated values
     * */
    public void save(User user){
        userRepo.save(user);
    }


    /**
     * only updates username and password
     * */
    public void updateUsernameAndPass(String userName,User newUser){
        User user = userRepo.findByUserName(userName);
        user.setUserName(newUser.getUserName());
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        userRepo.save(user);
    }

    public void deleteByUserName(String userName){
        userRepo.deleteByUserName(userName);
    }

    public User findByUserName(String userName){
       return userRepo.findByUserName(userName);
    }


}

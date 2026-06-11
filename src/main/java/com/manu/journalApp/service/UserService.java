package com.manu.journalApp.service;

import com.manu.journalApp.entity.JournalEntry;
import com.manu.journalApp.entity.User;
import com.manu.journalApp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public void save(User user){
        userRepo.save(user);
    }

    public List<User> getAllUser(){
        return userRepo.findAll();
    }

    public Optional<User> findUserById(String uid){
        return userRepo.findById(uid);
    }

    public void deleteUserById(String uid){
        userRepo.deleteById(uid);
    }
    public User findByUserName(String userName){
       return userRepo.findByUserName(userName);
    }


}

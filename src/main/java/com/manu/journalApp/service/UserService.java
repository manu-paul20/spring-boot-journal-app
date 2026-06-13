package com.manu.journalApp.service;

import com.manu.journalApp.entity.JournalEntry;
import com.manu.journalApp.entity.User;
import com.manu.journalApp.repository.UserRepo;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void save(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of("USER"));
        userRepo.save(user);
    }

    public void deleteByUserName(String userName){
        userRepo.deleteByUserName(userName);
    }

    public User findByUserName(String userName){
       return userRepo.findByUserName(userName);
    }


}

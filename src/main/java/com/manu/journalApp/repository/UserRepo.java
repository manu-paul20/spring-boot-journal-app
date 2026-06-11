package com.manu.journalApp.repository;

import com.manu.journalApp.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User,String> {
    User findByUserName(String userName);
}

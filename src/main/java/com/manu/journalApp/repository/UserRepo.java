package com.manu.journalApp.repository;

import com.manu.journalApp.entity.User;
import lombok.NonNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


public interface UserRepo extends MongoRepository<User,String> {
    User findByUserName(@NonNull String userName);

    void deleteByUserName(@NonNull String userName);
}

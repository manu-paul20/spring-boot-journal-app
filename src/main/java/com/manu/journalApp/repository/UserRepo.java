package com.manu.journalApp.repository;

import com.manu.journalApp.entity.User;
import lombok.NonNull;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User,String> {
    User findByUserName(@NonNull String userName);

    void deleteByUserName(@NonNull String userName);
}

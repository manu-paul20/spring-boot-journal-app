package com.manu.journalApp.controller;


import com.manu.journalApp.entity.User;
import com.manu.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody User user) {
        try {
            userService.save(user);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DuplicateKeyException e) {
            return new ResponseEntity<>("User already exists", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("id/{uid}")
    public ResponseEntity<?> deleteUser(@PathVariable String uid) {
        try {
            userService.deleteUserById(uid);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("id/{uid}")
    public ResponseEntity<User> findUserById(@PathVariable String uid) {
        try {
            Optional<User> user = userService.findUserById(uid);
            if (user.isPresent()) {
                return new ResponseEntity<>(user.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

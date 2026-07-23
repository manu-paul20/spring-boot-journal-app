package com.manu.journalApp.controller;


import com.manu.journalApp.apiresponse.WeatherResponse;
import com.manu.journalApp.entity.User;
import com.manu.journalApp.service.UserService;
import com.manu.journalApp.service.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private WeatherService weatherService;


    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody User user){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            userService.updateUsernameAndPass(userName,user);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<User> getUser(){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            User userInDb = userService.findByUserName(userName);
            return new ResponseEntity<>(userInDb,HttpStatus.OK);
        } catch (Exception e) {
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        try {
            userService.deleteByUserName(userName);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //Just ignore this controller

    @GetMapping("/greet/{city}")
    public String greet(@PathVariable String city){
        try{
            var response = weatherService.getWeather(city);
            return String.format("Temp = %f %s",response.temperature.metric.value,response.temperature.metric.unit);
        }catch (Exception e){
            log.error("ERROR = ",e);
            return "";
        }
    }
}

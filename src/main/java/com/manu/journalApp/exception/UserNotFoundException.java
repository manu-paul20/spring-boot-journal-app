package com.manu.journalApp.exception;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(){
        super("Specified user not found");
    }
}

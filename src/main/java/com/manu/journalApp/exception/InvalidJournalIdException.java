package com.manu.journalApp.exception;

public class InvalidJournalIdException extends Exception{
    public InvalidJournalIdException(){
        super("User dont have this journal entry");
    }
}

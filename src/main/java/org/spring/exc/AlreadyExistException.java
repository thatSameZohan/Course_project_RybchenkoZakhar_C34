package org.spring.exc;

import lombok.Getter;

@Getter
public class AlreadyExistException extends IllegalStateException{

    private String message;

    public AlreadyExistException(String message){
        this.message=message;
    }
}
package org.spring.exc;

import lombok.Getter;

@Getter
public class PersonAlreadyExistException extends IllegalStateException{
    String  message="Такой пользователь уже существует";
}
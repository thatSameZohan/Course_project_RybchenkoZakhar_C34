package org.spring.controller;

import org.spring.exc.PersonAlreadyExistException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(PersonAlreadyExistException.class)
    public String error1(Model model, PersonAlreadyExistException exc) {
        model.addAttribute("message", exc.getMessage());
        return "error.html";
    }
}

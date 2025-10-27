package org.spring.controller;

import org.spring.exc.AlreadyExistException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(AlreadyExistException.class)
    public String error1(Model model, AlreadyExistException exc) {
        model.addAttribute("message", exc.getMessage());
        return "error.html";
    }
}

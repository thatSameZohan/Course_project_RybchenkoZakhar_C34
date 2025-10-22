package org.spring.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/ingoing")
@Controller
public class LoginController {

    @GetMapping
    public String loginPage(){
        return "login.html";
    }
}

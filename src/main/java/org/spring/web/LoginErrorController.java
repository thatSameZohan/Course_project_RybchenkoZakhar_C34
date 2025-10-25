package org.spring.web;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/error")
@Controller
public class LoginErrorController {

    @GetMapping
    public String errorLoginPage(HttpServletRequest request, Model model) {
        model.addAttribute("message",request.getAttribute("message"));
        return "error.html";
    }
}

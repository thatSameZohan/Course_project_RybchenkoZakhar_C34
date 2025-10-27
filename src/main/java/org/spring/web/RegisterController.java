package org.spring.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.spring.dto.PersonDto;
import org.spring.service.impl.PersonServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/register")
@Controller
public class RegisterController {

    private final PersonServiceImpl service;

    @GetMapping
    public String registerPage(Model model) {
        model.addAttribute("person",new PersonDto());
        return "register.html";
    }

    @PostMapping
    public String saveUser(
            @Valid @ModelAttribute(name = "person") PersonDto dto,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register.html";
        }
        service.save(dto);
        return "login.html";
    }
}

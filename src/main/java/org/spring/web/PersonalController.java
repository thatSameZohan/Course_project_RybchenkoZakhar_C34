package org.spring.web;

import lombok.RequiredArgsConstructor;
import org.spring.repository.CourseRepository;
import org.spring.repository.PersonRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@RequiredArgsConstructor
@RequestMapping("/personal")
@Controller
public class PersonalController {

    private final CourseRepository courseRepo;
    private final PersonRepository personRepo;

    @GetMapping
    public String personalPage(Principal principal, Model model){
        var entity = personRepo.findByUsername(principal.getName()).orElseThrow();
        var courses=entity.getCourses();
        model.addAttribute("username",entity.getUsername());
        model.addAttribute("courses",courses);
        return "personal.html";
    }

    @GetMapping("/lessons/{name}")
    public String lessonsPage(@PathVariable String name, Model model){
        var courseEntity=courseRepo.findByName(name).orElseThrow();
        model.addAttribute("course",courseEntity);
        model.addAttribute("lessons", courseEntity.getLessons());
        return "lessons.html";
    }
}

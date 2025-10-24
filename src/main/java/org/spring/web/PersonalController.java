package org.spring.web;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.spring.model.PersonEntity;
import org.spring.repository.CourseRepository;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/personal")
@Controller
public class PersonalController {

    private final CourseRepository courseRepo;

    @GetMapping
    public String personalPage(HttpSession session, Model model){
        var context = (SecurityContext)session.getAttribute("SPRING_SECURITY_CONTEXT");
        var entity = (PersonEntity)context.getAuthentication().getPrincipal();
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

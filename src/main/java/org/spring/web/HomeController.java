package org.spring.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.spring.dto.CourseSearchDto;
import org.spring.dto.LeadDto;
import org.spring.repository.CourseRepository;
import org.spring.service.CourseService;
import org.spring.service.LeadService;
import org.spring.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequiredArgsConstructor
@RequestMapping("/")
@Controller
public class HomeController {

    private final CourseService courseService;
    private final CourseRepository courseRepo;
    private final LeadService leadService;
    private final PersonService personService;

    @GetMapping
    public String homePage(Model model) {
        model.addAttribute("lead", new LeadDto());
        return "home.html";
    }

    @GetMapping("/courses")
    public String courses(Model model) {
        model.addAttribute("courses", courseService.findAll());
        return "home_courses.html";
    }

    @GetMapping("/courses/search")
    public String searchCourse(
            CourseSearchDto searchDto,
            Model model) {
        var result = courseService.findByCriteria(searchDto);
        model.addAttribute("courses", result);
        return "home_courses.html";
    }
    @GetMapping("/courses/{name}")
    public String lessonsPage(@PathVariable String name, Model model){
        var courseEntity=courseRepo.findByName(name).orElseThrow();
        model.addAttribute("course",courseEntity);
        return "course.html";
    }

    @PostMapping("/buy")
    public String buyCourse(Principal principal, String name, Model model){
            personService.addCourse(principal.getName(),name);
            model.addAttribute("name",name);
            return "course_buy.html";
        }

    @PostMapping("/lead")
    public String saveLead(@Valid @ModelAttribute(name = "lead") LeadDto dto,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "home.html";
        }
        leadService.save(dto);
        return "lead.html";
    }
}

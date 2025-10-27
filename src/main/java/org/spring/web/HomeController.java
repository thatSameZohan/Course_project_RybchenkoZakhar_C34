package org.spring.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.spring.dto.CourseSearchDto;
import org.spring.dto.LeadDto;
import org.spring.service.CourseService;
import org.spring.service.LeadService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/")
@Controller
public class HomeController {

    private final CourseService courseService;
    private final LeadService leadService;

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

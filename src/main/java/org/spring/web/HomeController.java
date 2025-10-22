package org.spring.web;

import lombok.RequiredArgsConstructor;
import org.spring.dto.CourseSearchDto;
import org.spring.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/")
@Controller
public class HomeController {

    private final CourseService courseService;

    @GetMapping
    public String homePage(Model model) {
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
}

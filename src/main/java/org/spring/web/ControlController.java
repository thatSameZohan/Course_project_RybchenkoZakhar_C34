package org.spring.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.spring.dto.CourseDto;
import org.spring.dto.CourseSearchDto;
import org.spring.dto.PersonDto;
import org.spring.dto.PersonSearchDto;
import org.spring.model.CourseEntity;
import org.spring.service.CourseService;
import org.spring.service.PersonService;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/control")
@Controller
public class ControlController {

    private final PersonService personService;
    private final CourseService courseService;

    @GetMapping
    public String home(Model model) {
        return "control.html";
    }

    @GetMapping("/users")
    public String persons(Model model) {
        model.addAttribute("persons", personService.findAll());
        model.addAttribute("courses", courseService.findAll());
        return "control_users.html";
    }

    @GetMapping("/users/search")
    public String searchPerson(
            PersonSearchDto searchDto,
            Model model) {
        var result=personService.findByCriteria(searchDto);
        model.addAttribute("persons", result);
        return "control_users.html";
    }

    @PostMapping("/users/delete")
    public String deletePerson(
            PersonSearchDto searchDto,
            Model model) {
        personService.delete(searchDto.getUsername());
        model.addAttribute("persons", personService.findAll());
        return "control_users.html";
    }
    @PostMapping("/users/block")
    public String blockPerson(
            PersonSearchDto searchDto,
            Model model) {
        personService.block(searchDto.getUsername());
        model.addAttribute("persons", personService.findAll());
        return "control_users.html";
    }

    @PostMapping("/users/addcourse")
    public String addCourseToPerson(PersonSearchDto personSearchDto,
                                    Model model,
                                    CourseSearchDto courseSearchDto,
                                    Model coursesModel) {
        personService.addCourse(personSearchDto.getUsername(),courseSearchDto.getName());
        model.addAttribute("persons", personService.findAll());
        model.addAttribute("courses", courseService.findAll());
        return "control_users.html";
    }

    @PostMapping("/users/deletecourse")
    public String deleteCourseFromPerson(PersonSearchDto personSearchDto,
                                    Model model,
                                    CourseSearchDto courseSearchDto,
                                    Model coursesModel) {
        personService.deleteCourse(personSearchDto.getUsername(),courseSearchDto.getName());
        model.addAttribute("persons", personService.findAll());
        model.addAttribute("courses", courseService.findAll());
        return "control_users.html";
    }

    @GetMapping("/courses")
    public String courses(Model model) {
        model.addAttribute("course",new CourseDto());
        model.addAttribute("courses", courseService.findAll());
        return "control_courses.html";
    }

    @PostMapping("/courses/save")
    public String saveCourse(@Valid @ModelAttribute(name = "course")CourseDto dto,
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("courses", courseService.findAll());
            return "control_courses.html";
        }
            courseService.save(dto);
            model.addAttribute("courses", courseService.findAll());
            return "control_courses.html";
    }

    @GetMapping("/courses/search")
    public String searchCourse(
            @ModelAttribute(name = "course")CourseDto dto,
            CourseSearchDto searchDto,
            Model model) {
        var result = courseService.findByCriteria(searchDto);
        model.addAttribute("courses", result);
        return "control_courses.html";
    }

    @PostMapping("/courses/delete")
    public String deleteCourse(
            @ModelAttribute(name = "course")CourseDto dto,
            CourseSearchDto searchDto,
            Model model) {
        courseService.delete(searchDto.getName());
        model.addAttribute("courses", courseService.findAll());
        return "control_courses.html";
    }





}

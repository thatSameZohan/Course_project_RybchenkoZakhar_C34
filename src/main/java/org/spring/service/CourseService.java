package org.spring.service;

import org.spring.dto.CourseDto;
import org.spring.dto.CourseSearchDto;
import org.spring.dto.PersonDto;
import org.spring.dto.PersonSearchDto;
import org.spring.model.CourseEntity;

import java.util.List;

public interface CourseService {

    void save(CourseDto dto);

    void delete(String name);

    List<CourseEntity> findAll();

    List<CourseEntity> findByCriteria(CourseSearchDto dto);
}

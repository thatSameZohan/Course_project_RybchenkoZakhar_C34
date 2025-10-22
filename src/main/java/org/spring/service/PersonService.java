package org.spring.service;

import org.spring.dto.PersonDto;
import org.spring.dto.PersonSearchDto;
import org.spring.model.PersonEntity;

import java.util.List;

public interface PersonService {

    void save(PersonDto dto);

    void block(String username);

    void delete(String username);

    void addCourse(String username, String courseName);

    void deleteCourse(String username, String courseName);

    List<PersonEntity> findAll();

    List<PersonEntity> findByCriteria(PersonSearchDto dto);


}

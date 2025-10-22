package org.spring.mapper;

import org.mapstruct.Mapper;
import org.spring.dto.CourseDto;
import org.spring.dto.PersonDto;
import org.spring.model.CourseEntity;
import org.spring.model.PersonEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    PersonEntity toPersonEntity(PersonDto dto);
    PersonDto toPersonDto(PersonEntity entity);
    List<PersonDto> toPersonDto(List<PersonEntity> PersonEntities);
    List<CourseDto> toCourseDto(List<CourseEntity> CourseEntities);
}

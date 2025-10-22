package org.spring.mapper;

import org.mapstruct.Mapper;
import org.spring.dto.CourseDto;
import org.spring.dto.LessonDto;
import org.spring.dto.PersonDto;
import org.spring.model.CourseEntity;
import org.spring.model.LessonEntity;
import org.spring.model.PersonEntity;

import java.util.List;
@Mapper(componentModel = "spring")
public interface CourseMapper {

    CourseEntity toCourseEntity(CourseDto dto);
    CourseDto toCourseDto(CourseEntity entity);
    List<CourseDto> toCourseDto(List<CourseEntity> courseEntities);
    List<PersonDto> toPersonDto(List<PersonEntity> personEntities);
    List<LessonDto> toLessonDto(List<LessonEntity> lessonEntities);

}

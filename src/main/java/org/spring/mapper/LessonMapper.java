package org.spring.mapper;

import org.mapstruct.Mapper;
import org.spring.dto.LessonDto;
import org.spring.model.LessonEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LessonMapper {

    LessonEntity toLessonEntity(LessonDto dto);
    LessonDto toLessonDto(LessonEntity entity);
    List<LessonDto> toLessonDto(List<LessonEntity> lessonEntities);
}

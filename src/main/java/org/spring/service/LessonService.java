package org.spring.service;

import org.spring.dto.LessonDto;

public interface LessonService {

    void saveLesson(String courseName, LessonDto dto);
}

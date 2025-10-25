package org.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.spring.dto.LessonDto;
import org.spring.mapper.LessonMapper;
import org.spring.model.LessonEntity;
import org.spring.repository.CourseRepository;
import org.spring.service.LessonService;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class LessonServiceImpl implements LessonService {

    private final CourseRepository courseRepo;
    private final LessonMapper lessonMapper;

    @Override
    public void saveLesson(String courseName,LessonDto dto) {
        var lessonEntity = lessonMapper.toLessonEntity(dto);
        var courseEntity = courseRepo.findByName(courseName).orElse(null);
        if (courseEntity != null) {
            var lessons=courseEntity.getLessons();
            for (LessonEntity lesson: lessons) {
                boolean checkNumber=lesson.getNumber().equals(lessonEntity.getNumber());
                boolean checkName=lesson.getName().equals(lessonEntity.getName());
                if(checkNumber|checkName){
                    return;
                }
            }
            lessons.add(lessonEntity);
            lessonEntity.setCourse(courseEntity);
            courseRepo.save(courseEntity);
        }
    }
}

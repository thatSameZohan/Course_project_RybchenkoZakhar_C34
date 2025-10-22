package org.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class LessonDto {

    private UUID id;

    private Integer number;

    private String name;

    private CourseDto course;
}

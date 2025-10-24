package org.spring.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity
@Table (name = "lessons")
public class LessonEntity {

    @Id
    @UuidGenerator
    private UUID id;

    private Integer number;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="course_id")
    @ToStringExclude
    private CourseEntity course;

    public LessonEntity(Integer number,String name) {
        this.number=number;
        this.name = name;
    }

    public String toString() {
        String var10000 = String.valueOf(this.getId());
        return "занятие " + this.getNumber()+ ": " + this.getName();
    }
}

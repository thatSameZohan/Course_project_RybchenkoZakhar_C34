package org.spring.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity
@Table(name = "courses")
public class CourseEntity {

    @Id
    @UuidGenerator
    private UUID id;

    private String name;

    private String description;

    @ManyToMany(mappedBy = "courses", fetch = FetchType.EAGER)
    @ToString.Exclude
    List<PersonEntity> persons=new ArrayList<>();

    public CourseEntity(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String toString() {
        String var10000 = String.valueOf(this.getId());
        return "курс: " + this.getName();
    }

}

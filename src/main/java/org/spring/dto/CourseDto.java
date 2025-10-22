package org.spring.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;
import java.util.UUID;
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString
public class CourseDto {

    private UUID id;
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @ToString.Exclude
    private List<PersonDto> persons;
}

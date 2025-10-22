package org.spring.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString
public class PersonDto {

    private UUID id;
    @NotBlank
    private String username;
    @NotBlank
    private String password;

    private String authority;

    private boolean isEnabled;
    @ToString.Exclude
    private List<CourseDto> courses;


}

package org.spring.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString
public class PersonDto {

    private UUID id;
    @Size(min = 1, max = 20)
    @NotBlank
    private String username;
    @Size(min = 1, max = 50)
    @NotBlank
    private String password;

    private String authority;

    private boolean isEnabled;
    @ToString.Exclude
    private List<CourseDto> courses;
}

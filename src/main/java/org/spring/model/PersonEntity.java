package org.spring.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity
@Table(name = "persons")
@ToString
public class PersonEntity implements UserDetails {

    @Id
    @UuidGenerator
    private UUID id;

    private String username;

    private String password;

    private String authority;
    @Column(name="is_enabled")
    private boolean isEnabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @ToString.Exclude
    @JoinTable(name = "persons_courses_join", joinColumns = @JoinColumn(name = "person_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
    List<CourseEntity> courses=new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(authority==null||authority.isBlank()){
            return List.of();
        }
        return Arrays.stream(authority.split(","))
                .map(SimpleGrantedAuthority::new)
                .toList();
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}

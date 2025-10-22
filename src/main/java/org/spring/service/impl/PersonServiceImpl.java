package org.spring.service.impl;

import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.spring.config.AppSecurityConfig;
import org.spring.dto.PersonDto;
import org.spring.dto.PersonSearchDto;
import org.spring.mapper.CourseMapper;
import org.spring.mapper.PersonMapper;
import org.spring.model.CourseEntity;
import org.spring.model.PersonEntity;
import org.spring.repository.CourseRepository;
import org.spring.repository.PersonRepository;
import org.spring.service.CourseService;
import org.spring.service.PersonService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService, UserDetailsService {

    private final PersonRepository repo;
    private final CourseRepository courseRepo;
    private final PersonMapper mapper;
    private final CourseMapper courseMapper;
    private final AppSecurityConfig config;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repo.findByUsername(username)
                .orElseThrow();
    }

    @Override
    public void save(PersonDto dto) {
        dto.setPassword(config.passwordEncoder().encode(dto.getPassword()));;
        var entity = mapper.toPersonEntity(dto);
        entity.setAuthority("read");
        entity.setEnabled(true);
        repo.save(entity);
    }

    @Override
    public void block(String username) {
        PersonEntity entity = repo.findByUsername(username).orElse(null);
        if(entity!=null){
            if (!entity.isEnabled()){
                entity.setEnabled(true);
            } else {
                entity.setEnabled(false);
            }
            repo.save(entity);
        }
    }

    @Override
    public void delete(String username) {
        PersonEntity entity = repo.findByUsername(username).orElse(null);
        if(entity!=null){
            repo.delete(entity);
        }
    }

    @Override
    public void addCourse(String username, String courseName) {
        var personEntity = repo.findByUsername(username).orElse(null);
        var courseEntity = courseRepo.findByName(courseName).orElseThrow();
        if (personEntity != null) {
            if (!personEntity.getCourses().contains(courseEntity)) {
                personEntity.getCourses().add(courseEntity);
                courseEntity.getPersons().add(personEntity);
                repo.save(personEntity);
            }
        }
    }

    @Override
    public void deleteCourse(String username, String courseName) {
        PersonEntity entity = repo.findByUsername(username).orElse(null);
        if (entity != null) {
        entity.getCourses().remove(courseRepo.findByName(courseName).orElseThrow());
        repo.save(entity);
        }
    }

    @Override
    public List<PersonEntity> findAll() {
        List<PersonEntity> entities = repo.findAll();
        return entities;
    }

    @Override
    public List<PersonEntity> findByCriteria(PersonSearchDto dto) {
        Specification<PersonEntity> specification = createSpecification(dto);
        List<PersonEntity> result = repo.findAll(specification);
        return result;
    }

    private Specification<PersonEntity> createSpecification(PersonSearchDto dto) {
        return (root, query, builder) ->{
            List<Predicate> predicates = new ArrayList<>();
            if(StringUtils.isNoneBlank(dto.getUsername())){
                predicates.add(builder.equal(root.get("username"), dto.getUsername()));
            }
            if(StringUtils.isNoneBlank(dto.getAuthority())){
                predicates.add(builder.like(root.get("authority"), "%"+dto.getAuthority()+"%"));
            }
            return builder.and(predicates.toArray(new Predicate[]{}));
        };
    }
}


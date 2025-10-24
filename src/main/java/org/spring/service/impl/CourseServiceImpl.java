package org.spring.service.impl;

import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.spring.dto.CourseDto;
import org.spring.dto.CourseSearchDto;
import org.spring.mapper.CourseMapper;
import org.spring.model.CourseEntity;
import org.spring.repository.CourseRepository;
import org.spring.service.CourseService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository repo;
    private final CourseMapper mapper;

    @Override
    public void save(CourseDto dto) {
        var courseEntity = mapper.toCourseEntity(dto);
        if(repo.findByName(dto.getName()).isEmpty()) {
            repo.save(courseEntity);
        }
    }

    @Override
    public void delete(String name) {
        var byName=repo.findByName(name).orElse(null);
        if(byName!=null){
            repo.delete(byName);
        }
    }

    @Override
    public List<CourseEntity> findAll() {
        var entities = repo.findAll();
        return entities;
    }

    @Override
    public List<CourseEntity> findByCriteria(CourseSearchDto dto) {
        Specification<CourseEntity> specification = createSpecification(dto);
        List<CourseEntity> result = repo.findAll(specification);
        return result;
    }

    private Specification<CourseEntity> createSpecification(CourseSearchDto dto) {
        return (root, query, builder) ->{
            List<Predicate> predicates = new ArrayList<>();
            if(StringUtils.isNoneBlank(dto.getName())){
                predicates.add(builder.equal(root.get("name"), dto.getName()));
            }
            if(StringUtils.isNoneBlank(dto.getDescription())){
                predicates.add(builder.like(root.get("description"), "%"+dto.getDescription()+"%"));
            }
            return builder.and(predicates.toArray(new Predicate[]{}));
        };
    }
}

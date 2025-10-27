package org.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.spring.dto.LeadDto;
import org.spring.exc.AlreadyExistException;
import org.spring.mapper.LeadMapper;
import org.spring.model.LeadEntity;
import org.spring.repository.LeadRepository;
import org.spring.service.LeadService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LeadServiceImpl implements LeadService {

    private final LeadRepository repo;
    private final LeadMapper mapper;

    @Override
    public void save(LeadDto dto) {
        boolean checkPhone = false;
        boolean checkEmail = false;
        for(LeadEntity lead : repo.findAll()){
            if(checkPhone||checkEmail){
                break;
            }
            checkEmail=lead.getEmail().equals(dto.getEmail());
            checkPhone = lead.getPhone().equals(dto.getPhone());
        }
        if (!checkPhone&&!checkEmail) {
            var entity = mapper.toEntity(dto);
            repo.save(entity);
        } else throw new AlreadyExistException("Вы уже оставили заявку.");
    }

    @Override
    public List<LeadEntity> findAll() {
        return repo.findAll();
    }

    @Override
    public void delete(UUID id) {
        var result=repo.findById(id).orElse(null);
        if (result!=null){
            repo.delete(result);
        }
    }
}


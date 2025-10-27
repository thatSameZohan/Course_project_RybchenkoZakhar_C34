package org.spring.service;

import org.spring.dto.LeadDto;
import org.spring.model.LeadEntity;

import java.util.List;
import java.util.UUID;

public interface LeadService {

    List<LeadEntity> findAll();

    void save(LeadDto dto);

    void delete(UUID id);
}

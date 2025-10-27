package org.spring.mapper;

import org.mapstruct.Mapper;
import org.spring.dto.LeadDto;
import org.spring.model.LeadEntity;

@Mapper(componentModel = "spring")
public interface LeadMapper {

    LeadEntity toEntity(LeadDto dto);
}

package com.alkemy.disney.mapper;

import com.alkemy.disney.dto.GenderDTO;
import com.alkemy.disney.dto.MovieDTO;
import com.alkemy.disney.entity.GenderEntity;
import com.alkemy.disney.entity.MovieEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GenderMapper {
    public GenderEntity genderDTO2Entity(GenderDTO dto){
        GenderEntity genderEntity = new GenderEntity();
        genderEntity.setName(dto.getName());
        genderEntity.setImage(dto.getImage());
        genderEntity.setDeleted(dto.getDeleted());
        return genderEntity;
    }

    public GenderDTO genderEntity2DTO(GenderEntity entity){
        GenderDTO dto = new GenderDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setImage(entity.getImage());
        dto.setDeleted(entity.getDeleted());
        return dto;
    }

    public List<GenderDTO> genderEntityList2DTOList(List<GenderEntity> entities){
        List<GenderDTO> dtos = new ArrayList<>();
        for(GenderEntity entity: entities){
            dtos.add(this.genderEntity2DTO(entity));
        }
        return dtos;
    }
    public GenderEntity update(GenderEntity entity, GenderDTO dto){
        entity.setId(dto.getId());
        entity.setImage(dto.getImage());
        entity.setName(dto.getName());
        entity.setDeleted(dto.getDeleted());
        return entity;
    }
}

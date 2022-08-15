package com.alkemy.disney.service.impl;

import com.alkemy.disney.dto.GenderDTO;
import com.alkemy.disney.dto.MovieDTO;
import com.alkemy.disney.entity.GenderEntity;
import com.alkemy.disney.entity.MovieEntity;
import com.alkemy.disney.mapper.GenderMapper;
import com.alkemy.disney.repository.GenderRepository;
import com.alkemy.disney.service.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenderServiceImpl implements GenderService {
    @Autowired
    private GenderMapper genderMapper;
    @Autowired
    private GenderRepository genderRepository;

    @Override
    public GenderDTO save(GenderDTO dto) {
        GenderEntity entity = genderMapper.genderDTO2Entity(dto);
        GenderEntity entitySaved = genderRepository.save(entity);
        GenderDTO result = genderMapper.genderEntity2DTO(entitySaved);
        return result;
    }

    @Override
    public List<GenderDTO> getAllGenders() {
        List<GenderEntity> entities = genderRepository.findAll();
        List<GenderDTO> result = genderMapper.genderEntityList2DTOList(entities);
        return result;
    }

    public GenderDTO update(Long id, GenderDTO gender){
        GenderEntity entityId = genderRepository.getReferenceById(id);
        GenderEntity entity = genderMapper.update(entityId,gender);
        GenderEntity entityUpdated = genderRepository.save(entity);
        GenderDTO result = genderMapper.genderEntity2DTO(entityUpdated);
        return result;
    }

    @Override
    public void delete(Long id) {
    this.genderRepository.deleteById(id);
    }
}

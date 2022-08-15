package com.alkemy.disney.service.impl;

import com.alkemy.disney.dto.FigureBasicDTO;
import com.alkemy.disney.dto.FigureDTO;
import com.alkemy.disney.entity.FigureEntity;
import com.alkemy.disney.entity.MovieEntity;
import com.alkemy.disney.mapper.FigureMapper;
import com.alkemy.disney.repository.FigureRepository;
import com.alkemy.disney.service.FigureService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class FigureServiceImpl implements FigureService {
    @Autowired
    private FigureMapper figureMapper;

    @Autowired
    private FigureRepository figureRepository;

    public FigureDTO save(FigureDTO dto) {
        FigureEntity entity = figureMapper.figureDTO2Entity(dto, false);
        FigureEntity entitySaved = figureRepository.save(entity);
        FigureDTO result = figureMapper.figureEntity2DTO(entitySaved, true);
        return result;
    }

    public List<FigureDTO> getAllFigures() {
        List<FigureEntity> entities = figureRepository.findAll();
        List<FigureDTO> result = figureMapper.figureEntityList2DTOList(entities, true);
        return result;
    }

    @Override
    public FigureDTO update(Long id, FigureDTO figure) {
        FigureEntity entityId = figureRepository.getReferenceById(id);
        FigureEntity entity = figureMapper.update(entityId,figure);
        FigureEntity entityUpdated = figureRepository.save(entity);
        FigureDTO result = figureMapper.figureEntity2DTO(entityUpdated, true);
        return result;
    }

    @Override
    public void delete(Long id) {
        this.figureRepository.deleteById(id);
    }

    public List<FigureBasicDTO> search(String name, Integer age,Double weight){
        List<FigureEntity> entities = figureRepository.findByNameAndAgeOrWeight(name, age, weight);
        List<FigureBasicDTO> result = figureMapper.figureEntitySet2DTOBasicList(entities);
        return result;
    }


}

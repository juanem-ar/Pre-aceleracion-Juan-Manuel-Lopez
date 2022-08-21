package com.alkemy.disney.service.impl;

import com.alkemy.disney.dto.FigureBasicDTO;
import com.alkemy.disney.dto.FigureDTO;
import com.alkemy.disney.dto.FigureFiltersDTO;
import com.alkemy.disney.entity.FigureEntity;
import com.alkemy.disney.exception.ParamNotFound;
import com.alkemy.disney.mapper.FigureMapper;
import com.alkemy.disney.repository.FigureRepository;
import com.alkemy.disney.repository.specifications.FigureSpecification;
import com.alkemy.disney.service.FigureService;

import com.alkemy.disney.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class FigureServiceImpl implements FigureService {
    @Autowired
    private FigureMapper figureMapper;
    @Autowired
    private FigureSpecification figureSpecification;

    @Autowired
    private MovieService movieService;
    @Autowired
    private FigureRepository figureRepository;

    public FigureDTO save(FigureDTO dto) {
        FigureEntity entity = figureMapper.figureDTO2Entity(dto, false);
        FigureEntity entitySaved = figureRepository.save(entity);
        FigureDTO result = figureMapper.figureEntity2DTO(entitySaved, true);
        return result;
    }
    public FigureDTO getFigureById(Long id) {
        isCorrect(id);
        FigureEntity entity = figureRepository.getReferenceById(id);
        FigureDTO result = figureMapper.figureEntity2DTO(entity, true);
        return result;
    }
    @Override
    public FigureDTO update(Long id, FigureDTO figure) {
        isCorrect(id);
        FigureEntity entityId = figureRepository.getReferenceById(id);
        FigureEntity entity = figureMapper.update(entityId,figure);
        FigureEntity entityUpdated = figureRepository.save(entity);
        FigureDTO result = figureMapper.figureEntity2DTO(entityUpdated, true);
        return result;
    }
    @Override
    public void delete(Long id) {
        isCorrect(id);
        this.figureRepository.deleteById(id);
    }
    public List<FigureBasicDTO> getByFilters(String name, String age, String weight, Set<Long> movies, String order){
        FigureFiltersDTO filtersDTO = new FigureFiltersDTO(name, age, weight, movies, order);
        List<FigureEntity> entities = this.figureRepository.findAll(this.figureSpecification.getByFilters(filtersDTO));
        List<FigureBasicDTO> dtos = this.figureMapper.figureEntitySet2DTOBasicList(entities);
        return dtos;
    }
    public void isCorrect(Long id){
        if(!figureRepository.existsById(id)){
            throw new ParamNotFound("Invalid id");
        }
    }
}

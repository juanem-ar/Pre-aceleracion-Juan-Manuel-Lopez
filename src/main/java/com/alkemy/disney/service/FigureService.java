package com.alkemy.disney.service;

import com.alkemy.disney.dto.FigureBasicDTO;
import com.alkemy.disney.dto.FigureDTO;
import com.alkemy.disney.entity.FigureEntity;
import com.alkemy.disney.entity.MovieEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface FigureService {
    FigureDTO save(FigureDTO dto);
    List<FigureDTO> getAllFigures();
    FigureDTO update(Long id, FigureDTO figure);
    void delete (Long id);

    List<FigureBasicDTO> search(String name, Integer age,Double weight);

}

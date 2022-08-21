package com.alkemy.disney.service;

import com.alkemy.disney.dto.FigureBasicDTO;
import com.alkemy.disney.dto.FigureDTO;

import java.util.List;
import java.util.Set;

public interface FigureService {
    FigureDTO save(FigureDTO dto);
    FigureDTO getFigureById(Long id);
    FigureDTO update(Long id, FigureDTO figure);
    void delete (Long id);
    List<FigureBasicDTO> getByFilters(String name, String age, String weight, Set<Long> movies, String order);

    void isCorrect(Long id);

}

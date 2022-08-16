package com.alkemy.disney.service.impl;

import com.alkemy.disney.dto.*;
import com.alkemy.disney.entity.FigureEntity;
import com.alkemy.disney.entity.GenderEntity;
import com.alkemy.disney.entity.MovieEntity;
import com.alkemy.disney.exception.ParamNotFound;
import com.alkemy.disney.mapper.GenderMapper;
import com.alkemy.disney.mapper.MovieMapper;
import com.alkemy.disney.repository.FigureRepository;
import com.alkemy.disney.repository.GenderRepository;
import com.alkemy.disney.repository.MovieRepository;
import com.alkemy.disney.repository.specifications.MovieSpecification;
import com.alkemy.disney.service.FigureService;
import com.alkemy.disney.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieMapper movieMapper;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private MovieSpecification movieSpecification;
    @Autowired
    private FigureRepository figureRepository;

    @Override
    public MovieDTO save(MovieDTO dto) {
        MovieEntity entity = movieMapper.movieDTO2Entity(dto, true);
        MovieEntity entitySaved = movieRepository.save(entity);
        MovieDTO result = movieMapper.movieEntity2DTO(entitySaved, true);
        return result;
    }
    @Override
    public MovieDTO getMovieById(Long id) {
        isCorrect(id);
        MovieEntity entity = movieRepository.getReferenceById(id);
        MovieDTO result = movieMapper.movieEntity2DTO(entity, true);
        return result;
    }


    public MovieDTO update(Long id, MovieDTO movie) {
        isCorrect(id);
        MovieEntity entityId = movieRepository.getReferenceById(id);
        MovieEntity entity = movieMapper.update(entityId,movie);
        MovieEntity entityUpdated = movieRepository.save(entity);
        MovieDTO result = movieMapper.movieEntity2DTO(entityUpdated, true);
        return result;
    }
    @Override
    public void delete(Long id) {
        isCorrect(id);
        this.movieRepository.deleteById(id);
    }

    @Override
    public MovieDTO addFigure(Long idMovie, Long idFigure) {
        areCorrect(idMovie, idFigure);
        MovieEntity movieEntity = movieRepository.getReferenceById(idMovie);
        FigureEntity figureEntity = figureRepository.getReferenceById(idFigure);
        movieEntity.addFigure(figureEntity);
        MovieEntity movieSaved = movieRepository.save(movieEntity);
        MovieDTO dto = movieMapper.movieEntity2DTO(movieSaved, true);
        return dto;
    }

    @Override
    public MovieDTO removeFigure(Long idMovie, Long idFigure) {
        areCorrect(idMovie, idFigure);
        MovieEntity movieEntity = movieRepository.getReferenceById(idMovie);
        FigureEntity figureEntity = figureRepository.getReferenceById(idFigure);
        movieEntity.removeFigure(figureEntity);
        MovieEntity movieSaved = movieRepository.save(movieEntity);
        MovieDTO dto = movieMapper.movieEntity2DTO(movieSaved, true);
        return dto;
    }
    public List<MovieBasicDTO> getByFilters(String title, String genre, String order){
        MovieFiltersDTO filtersDTO = new MovieFiltersDTO(title, genre, order);
        List<MovieEntity> entities = this.movieRepository.findAll(this.movieSpecification.getByFilters(filtersDTO));
        List<MovieBasicDTO> dtos = this.movieMapper.movieEntitySet2DTOBasicList(entities);
        return dtos;
    }
    public void isCorrect(Long id){
        if(!movieRepository.existsById(id)){
            throw new ParamNotFound("Invalid id");
        }
    }
    public void areCorrect(Long idMovie, Long idFigure){
        if(!movieRepository.existsById(idMovie)){
            throw new ParamNotFound("Invalid Movie ID");
        }else if(!movieRepository.existsById(idFigure)){
            throw new ParamNotFound("Invalid Character ID");
        }
    }
}

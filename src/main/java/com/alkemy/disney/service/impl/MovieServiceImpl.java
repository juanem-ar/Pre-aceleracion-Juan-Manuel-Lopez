package com.alkemy.disney.service.impl;

import com.alkemy.disney.dto.*;
import com.alkemy.disney.entity.FigureEntity;
import com.alkemy.disney.entity.MovieEntity;
import com.alkemy.disney.exception.ParamNotFound;
import com.alkemy.disney.mapper.MovieMapper;
import com.alkemy.disney.repository.FigureRepository;
import com.alkemy.disney.repository.MovieRepository;
import com.alkemy.disney.repository.specifications.MovieSpecification;
import com.alkemy.disney.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        isCorrect(id, "id");
        MovieEntity entity = movieRepository.getReferenceById(id);
        MovieDTO result = movieMapper.movieEntity2DTO(entity, true);
        return result;
    }
    public MovieDTO update(Long id, MovieDTO movie) {
        isCorrect(id, "id");
        MovieEntity entityId = movieRepository.getReferenceById(id);
        MovieEntity entity = movieMapper.update(entityId,movie);
        MovieEntity entityUpdated = movieRepository.save(entity);
        MovieDTO result = movieMapper.movieEntity2DTO(entityUpdated, true);
        return result;
    }
    @Override
    public void delete(Long id) {
        isCorrect(id, "id");
        this.movieRepository.deleteById(id);
    }
    @Override
    public void addFigures(Long idMovie, Long idFigure) {
        areCorrect(idMovie, "movie", idFigure, "character");
        MovieEntity movieEntity = this.movieRepository.getReferenceById(idMovie);
        FigureEntity figureEntity = this.figureRepository.getReferenceById(idFigure);
        movieEntity.getFigures().add(figureEntity);
        this.movieRepository.save(movieEntity);
    }

    @Override
    public void removeFigures(Long idMovie, Long idFigure) {
        areCorrect(idMovie, "movie", idFigure, "character");
        MovieEntity movieEntity = this.movieRepository.getReferenceById(idMovie);
        FigureEntity figureEntity = this.figureRepository.getReferenceById(idFigure);
        movieEntity.getFigures().remove(figureEntity);
        this.movieRepository.save(movieEntity);
    }
    public List<MovieBasicDTO> getByFilters(String title, String genre, String order){
        MovieFiltersDTO filtersDTO = new MovieFiltersDTO(title, genre, order);
        List<MovieEntity> entities = this.movieRepository.findAll(this.movieSpecification.getByFilters(filtersDTO));
        List<MovieBasicDTO> dtos = this.movieMapper.movieEntitySet2DTOBasicList(entities);
        return dtos;
    }
    public void isCorrect(Long id, String name){
        if(!movieRepository.existsById(id)){
            throw new ParamNotFound("Invalid " + name);
        }
    }

    @Override
    public void areCorrect(Long idOne, String nameOne, Long idTwo, String nameTwo) {
        if (!movieRepository.existsById(idOne)&&!figureRepository.existsById(idTwo)){
            throw new ParamNotFound("Invalid items");
        }else{
            isCorrect(idOne, nameOne);
            if (!figureRepository.existsById(idTwo)){
                throw new ParamNotFound("Invalid " + nameTwo );
            }
        }

    }

}

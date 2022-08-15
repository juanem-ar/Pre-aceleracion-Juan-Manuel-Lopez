package com.alkemy.disney.service.impl;

import com.alkemy.disney.dto.FigureDTO;
import com.alkemy.disney.dto.GenderDTO;
import com.alkemy.disney.dto.MovieDTO;
import com.alkemy.disney.entity.FigureEntity;
import com.alkemy.disney.entity.GenderEntity;
import com.alkemy.disney.entity.MovieEntity;
import com.alkemy.disney.mapper.GenderMapper;
import com.alkemy.disney.mapper.MovieMapper;
import com.alkemy.disney.repository.GenderRepository;
import com.alkemy.disney.repository.MovieRepository;
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

    @Override
    public MovieDTO save(MovieDTO dto) {
        MovieEntity entity = movieMapper.movieDTO2Entity(dto, true);
        MovieEntity entitySaved = movieRepository.save(entity);
        MovieDTO result = movieMapper.movieEntity2DTO(entitySaved, true);
        return result;
    }
    @Override
    public List<MovieDTO> getAllMovies() {
        List<MovieEntity> entities = movieRepository.findAll();
        List<MovieDTO> result = movieMapper.movieEntityList2DTOList(entities, true);
        return result;
    }
    public MovieDTO update(Long id, MovieDTO movie) {
        MovieEntity entityId = movieRepository.getReferenceById(id);
        MovieEntity entity = movieMapper.update(entityId,movie);
        MovieEntity entityUpdated = movieRepository.save(entity);
        MovieDTO result = movieMapper.movieEntity2DTO(entityUpdated, true);
        return result;
    }
    @Override
    public void delete(Long id) {
        this.movieRepository.deleteById(id);
    }
}

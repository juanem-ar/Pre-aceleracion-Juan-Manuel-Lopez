package com.alkemy.disney.service;

import com.alkemy.disney.dto.FigureDTO;
import com.alkemy.disney.dto.MovieDTO;

import java.util.List;

public interface MovieService {

    MovieDTO save(MovieDTO dto);
    List<MovieDTO> getAllMovies();
    MovieDTO update(Long id, MovieDTO movie);
    void delete (Long id);

}

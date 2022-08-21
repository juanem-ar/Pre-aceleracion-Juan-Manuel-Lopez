package com.alkemy.disney.service;

import com.alkemy.disney.dto.MovieBasicDTO;
import com.alkemy.disney.dto.MovieDTO;
import com.alkemy.disney.entity.MovieEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

public interface MovieService {

    MovieDTO save(MovieDTO dto);
    MovieDTO getMovieById(Long id);
    MovieDTO update(Long id, MovieDTO movie);
    void delete (Long id);
    void addFigures(Long idMovie, Long idFigure);
    void removeFigures(Long idMovie, Long idFigure);

    List<MovieBasicDTO> getByFilters(String title, String genre, String order);

    void isCorrect(Long id, String name);
}

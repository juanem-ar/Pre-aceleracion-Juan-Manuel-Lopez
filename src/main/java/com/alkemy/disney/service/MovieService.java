package com.alkemy.disney.service;

import com.alkemy.disney.dto.MovieBasicDTO;
import com.alkemy.disney.dto.MovieDTO;

import java.util.List;

public interface MovieService {

    MovieDTO save(MovieDTO dto);
    MovieDTO getMovieById(Long id);
    MovieDTO update(Long id, MovieDTO movie);
    void delete (Long id);
    MovieDTO addFigure(Long idMovie, Long idFigure);
    MovieDTO removeFigure(Long idMovie, Long idFigure);

    List<MovieBasicDTO> getByFilters(String title, String genre, String order);

    void isCorrect(Long id, String name);
    void areCorrect(Long idOne, String nameOne, Long idTwo, String nameTwo);
}

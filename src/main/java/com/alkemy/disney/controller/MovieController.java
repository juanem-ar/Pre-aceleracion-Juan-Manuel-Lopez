package com.alkemy.disney.controller;

import com.alkemy.disney.dto.MovieBasicDTO;
import com.alkemy.disney.dto.MovieDTO;
import com.alkemy.disney.service.FigureService;
import com.alkemy.disney.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("movies")
public class MovieController {
    @Autowired
    private MovieService movieService;
    @Autowired
    private FigureService figureService;

    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> getMovieById(@PathVariable Long id){
        MovieDTO movie = movieService.getMovieById(id);
        return ResponseEntity.ok().body(movie);
    }

    @PostMapping
    public ResponseEntity<MovieDTO> addMovie(@RequestBody MovieDTO movie){
        MovieDTO movieSaved = movieService.save(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(movieSaved);
    }
    @PutMapping("/{id}")
    public ResponseEntity<MovieDTO> update(@PathVariable Long id, @RequestBody MovieDTO movie) {
        MovieDTO movieUpdated = this.movieService.update(id, movie);
        return ResponseEntity.ok().body(movieUpdated);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id){
        this.movieService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/{idMovie}/characters/{idCharacter}")
    public ResponseEntity<MovieDTO> addFigure(@PathVariable Long idMovie, @PathVariable Long idCharacter){
        this.movieService.addFigure(idMovie, idCharacter);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }
    @DeleteMapping("/{idMovie}/characters/{idCharacter}")
    public ResponseEntity<MovieDTO> removeFigure(@PathVariable Long idMovie, @PathVariable Long idCharacter){
        this.movieService.removeFigure(idMovie, idCharacter);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
    @GetMapping
    public ResponseEntity<List<MovieBasicDTO>> getDetailsByFilters (
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false, defaultValue = "ASC") String order) {

        List<MovieBasicDTO> movies = this.movieService.getByFilters(title, genre, order);

        return ResponseEntity.ok().body(movies);
    }
}

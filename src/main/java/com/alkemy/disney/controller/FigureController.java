package com.alkemy.disney.controller;

import com.alkemy.disney.dto.FigureBasicDTO;
import com.alkemy.disney.dto.FigureDTO;
import com.alkemy.disney.service.FigureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("characters")
public class FigureController {

    @Autowired
    private FigureService figureService;

    @GetMapping("/{id}")
    public ResponseEntity<FigureDTO> getFigureById(@PathVariable Long id){
        FigureDTO figures = figureService.getFigureById(id);
        return ResponseEntity.ok().body(figures);
    }

    @PostMapping
    public ResponseEntity<FigureDTO> save(@RequestBody FigureDTO figure){
        FigureDTO figureSaved = figureService.save(figure);
        return ResponseEntity.status(HttpStatus.CREATED).body(figureSaved);
    }
    @PutMapping("/{id}")
    public ResponseEntity<FigureDTO> update(@PathVariable Long id, @RequestBody FigureDTO figure) {
        FigureDTO figureUpdated = this.figureService.update(id, figure);
        return ResponseEntity.ok().body(figureUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id){
        this.figureService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<List<FigureBasicDTO>> getDetailsByFilters (
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String age,
            @RequestParam(required = false) String weight,
            @RequestParam(required = false) Set<Long> movies,
            @RequestParam(required = false, defaultValue = "ASC") String order) {

        List<FigureBasicDTO> figures = this.figureService.getByFilters(name, age, weight, movies, order);

        return ResponseEntity.ok().body(figures);
    }
}

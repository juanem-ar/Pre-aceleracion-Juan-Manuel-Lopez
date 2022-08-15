package com.alkemy.disney.controller;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.alkemy.disney.dto.FigureBasicDTO;
import com.alkemy.disney.dto.FigureDTO;
import com.alkemy.disney.entity.MovieEntity;
import com.alkemy.disney.service.FigureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("characters")
public class FigureController {

    @Autowired
    private FigureService figureService;

    @GetMapping("/all")
    public ResponseEntity<List<FigureDTO>> getAll(){
        List<FigureDTO> figures = figureService.getAllFigures();
        return ResponseEntity.ok().body(figures);
    }

    @PostMapping //Agrego ("/cualquiera"), endpoint: POST localhost:8080/characters/cualquiera
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
    public ResponseEntity<List<FigureBasicDTO>> search (
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) Double weight
    ) {
        List<FigureBasicDTO> figures = this.figureService.search(name, age, weight);
        return ResponseEntity.ok().body(figures);
    }
}

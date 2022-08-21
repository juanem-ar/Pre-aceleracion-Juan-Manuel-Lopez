package com.alkemy.disney.controller;

import com.alkemy.disney.dto.GenderDTO;
import com.alkemy.disney.service.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("genders")
public class GenderController {

    @Autowired
    private GenderService genderService;

    @GetMapping
    public ResponseEntity<List<GenderDTO>> getAll(){
        List<GenderDTO> genders = genderService.getAllGenders();
        return ResponseEntity.ok().body(genders);
    }

    @PostMapping
    public ResponseEntity<GenderDTO> save(@RequestBody GenderDTO gender){
        GenderDTO genderSaved = genderService.save(gender);
        return ResponseEntity.status(HttpStatus.CREATED).body(genderSaved);
    }
    @PutMapping("/{id}")
    public ResponseEntity<GenderDTO> update(@PathVariable Long id, @RequestBody GenderDTO gender) {
        GenderDTO genderUpdated = this.genderService.update(id, gender);
        return ResponseEntity.ok().body(genderUpdated);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id){
        this.genderService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

package com.alkemy.disney.repository;

import com.alkemy.disney.entity.FigureEntity;
import com.alkemy.disney.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface FigureRepository extends JpaRepository<FigureEntity, Long> {

    List<FigureEntity> findByNameAndAgeOrWeight(String name, Integer age,Double weight);

}

package com.alkemy.disney.repository;

import com.alkemy.disney.entity.FigureEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FigureRepository extends JpaRepository<FigureEntity, Long> {

    List<FigureEntity> findAll(Specification<FigureEntity> spec);

}

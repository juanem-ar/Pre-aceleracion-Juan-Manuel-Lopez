package com.alkemy.disney.dto;

import com.alkemy.disney.entity.MovieEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FigureDTO {
    private Long id;
    private String image;
    private String name;
    private Integer age;
    private Double weight;
    private String history;
    private List<MovieEntity> movies;
}

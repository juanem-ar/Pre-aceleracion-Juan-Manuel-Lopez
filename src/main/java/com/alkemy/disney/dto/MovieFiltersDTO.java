package com.alkemy.disney.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class MovieFiltersDTO {
    private String title;
    private String genre;
    private String order;

    public MovieFiltersDTO(String title, String genre, String order){
        this.title = title;
        this.genre = genre;
        this.order = order;
    }
    public boolean isASC(){ return this.order.compareToIgnoreCase("ASC") == 0;}
}

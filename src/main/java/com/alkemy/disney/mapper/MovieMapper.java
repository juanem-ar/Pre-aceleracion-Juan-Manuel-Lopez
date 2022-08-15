package com.alkemy.disney.mapper;

import com.alkemy.disney.dto.FigureDTO;
import com.alkemy.disney.dto.MovieDTO;
import com.alkemy.disney.entity.FigureEntity;
import com.alkemy.disney.entity.MovieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class MovieMapper {
    @Autowired
    private FigureMapper figureMapper;
    public MovieEntity movieDTO2Entity(MovieDTO dto, boolean loadCharacters){
        MovieEntity entity = new MovieEntity();
        entity.setImage(dto.getImage());
        entity.setTitle(dto.getTitle());
        entity.setCreationDate(dto.getCreationDate());
        entity.setRating(dto.getRating());
        entity.setGenderId(dto.getGenderId());
        if(loadCharacters){
            entity.setFigures(dto.getFigures());
        }
        //List<FigureDTO> dtos = this.figureMapper.figureEntityList2DTOList(dto.getFigures(), false);
        //Set<FigureEntity> entities = this.figureMapper.figureDTOSet2EntitySet(dtos);
        return entity;
    }

    public MovieDTO movieEntity2DTO(MovieEntity entity, boolean loadCharacters){
        MovieDTO dto = new MovieDTO();
        dto.setId(entity.getId());
        dto.setImage(entity.getImage());
        dto.setTitle(entity.getTitle());
        dto.setCreationDate(entity.getCreationDate());
        dto.setRating(entity.getRating());
        dto.setGenderId(entity.getGenderId());
        if (loadCharacters){
            Set<FigureDTO> dtos = this.figureMapper.figureEntitySet2DTOSet(entity.getFigures(), false);
            Set<FigureEntity> entities = this.figureMapper.figureDTOSet2EntitySet(dtos, false);
            dto.setFigures(entities);
        }
        return dto;
    }
    public List<MovieDTO> movieEntityList2DTOList(List<MovieEntity> entities, boolean loadCharacters){
        List<MovieDTO> dtos = new ArrayList<>();
        for(MovieEntity entity: entities){
            dtos.add(this.movieEntity2DTO(entity, loadCharacters));
        }
        return dtos;
    }
    public List<MovieEntity> movieDTOList2EntityList(List<MovieDTO> dtos, boolean loadCharacters){
        List<MovieEntity> entities = new ArrayList<>();
        for(MovieDTO dto: dtos){
            entities.add(this.movieDTO2Entity(dto, loadCharacters));
        }
        return entities;
    }
    public MovieEntity update(MovieEntity entity, MovieDTO dto){
        entity.setId(dto.getId());
        entity.setImage(dto.getImage());
        entity.setTitle(dto.getTitle());
        entity.setCreationDate(dto.getCreationDate());
        entity.setRating(dto.getRating());
        entity.setGenderId(dto.getGenderId());
        entity.getFigures();
        return entity;
    }

}

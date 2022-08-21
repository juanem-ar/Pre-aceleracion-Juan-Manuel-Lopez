package com.alkemy.disney.mapper;

import com.alkemy.disney.dto.FigureBasicDTO;
import com.alkemy.disney.dto.FigureDTO;
import com.alkemy.disney.dto.MovieDTO;
import com.alkemy.disney.entity.FigureEntity;
import com.alkemy.disney.entity.MovieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class FigureMapper {
    @Autowired
    private MovieMapper movieMapper;
    public FigureEntity figureDTO2Entity(FigureDTO dto, boolean loadMovies){
        FigureEntity entity = new FigureEntity();
        entity.setImage(dto.getImage());
        entity.setName(dto.getName());
        entity.setAge(dto.getAge());
        entity.setWeight(dto.getWeight());
        entity.setHistory(dto.getHistory());
        if(loadMovies){
            entity.setMovies(dto.getMovies());
        }
        return entity;
    }
    public FigureDTO figureEntity2DTO(FigureEntity entity,boolean loadMovies){
        FigureDTO dto = new FigureDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setImage(entity.getImage());
        dto.setAge(entity.getAge());
        dto.setWeight(entity.getWeight());
        dto.setHistory(entity.getHistory());
        if (loadMovies){
            List<MovieDTO> dtos = this.movieMapper.movieEntityList2DTOList(entity.getMovies(), false);
            List<MovieEntity> entities = this.movieMapper.movieDTOList2EntityList(dtos, false);
            dto.setMovies(entities);
        }
        return dto;
    }

    public FigureBasicDTO figureEntity2BasicDTO(FigureEntity entity){
        FigureBasicDTO dto = new FigureBasicDTO();
        dto.setName(entity.getName());
        dto.setImage(entity.getImage());
        return dto;
    }

    public List<FigureBasicDTO> figureEntitySet2DTOBasicList(List<FigureEntity> entities){
        List<FigureBasicDTO> dtos = new ArrayList<>();
        for (FigureEntity entity: entities){
            dtos.add(this.figureEntity2BasicDTO(entity));
        }
        return dtos;
    }
    public Set<FigureDTO> figureEntitySet2DTOSet(Collection<FigureEntity> entities, boolean loadMovies){
        Set<FigureDTO> dtos = new HashSet<>();
        for (FigureEntity entity: entities){
            dtos.add(this.figureEntity2DTO(entity, false));
        }
        return dtos;
    }
    public Set<FigureEntity> figureDTOSet2EntitySet(Collection<FigureDTO> dtos, boolean loadMovies){
        Set<FigureEntity> entities = new HashSet<>();
        for (FigureDTO dto: dtos){
            entities.add(this.figureDTO2Entity(dto, loadMovies));
        }
        return entities;
    }

    public FigureEntity update(FigureEntity entity, FigureDTO dto){
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setImage(dto.getImage());
        entity.setAge(dto.getAge());
        entity.setWeight(dto.getWeight());
        entity.setHistory(dto.getHistory());
        entity.getMovies();
        return entity;
    }

}

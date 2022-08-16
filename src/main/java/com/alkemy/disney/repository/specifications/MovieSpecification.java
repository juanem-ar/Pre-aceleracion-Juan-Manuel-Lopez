package com.alkemy.disney.repository.specifications;

import com.alkemy.disney.dto.FigureFiltersDTO;
import com.alkemy.disney.dto.MovieFiltersDTO;
import com.alkemy.disney.entity.FigureEntity;
import com.alkemy.disney.entity.MovieEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class MovieSpecification {
    public Specification<MovieEntity> getByFilters(MovieFiltersDTO filtersDTO){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasLength(filtersDTO.getTitle())){
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("title")),
                                "%" + filtersDTO.getTitle().toLowerCase() + "%"
                        )
                );
            }

            if (StringUtils.hasLength(filtersDTO.getGenre())){
                Integer age = Integer.parseInt(filtersDTO.getGenre());

                predicates.add(
                        criteriaBuilder.equal(root.get("gender"), age)
                );
            }

            //Remove duplicates
            query.distinct(true);

            query.orderBy(
                    filtersDTO.isASC() ? criteriaBuilder.asc(root.get("creationDate")) : criteriaBuilder.desc(root.get("creationDate"))
            );
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}

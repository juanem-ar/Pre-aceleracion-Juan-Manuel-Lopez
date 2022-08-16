package com.alkemy.disney.repository.specifications;

import com.alkemy.disney.dto.FigureFiltersDTO;
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
import java.util.ArrayList;
import java.util.List;

@Component
public class FigureSpecification {
    public Specification<FigureEntity> getByFilters(FigureFiltersDTO filtersDTO){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasLength(filtersDTO.getName())){
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("name")),
                                "%" + filtersDTO.getName().toLowerCase() + "%"
                        )
                );
            }
            if (StringUtils.hasLength(filtersDTO.getAge())){

                Integer age = Integer.parseInt(filtersDTO.getAge());

                predicates.add(
                        criteriaBuilder.equal(root.get("age"), age)
                );
            }
            if (StringUtils.hasLength(filtersDTO.getWeight())){
                Double weight = Double.parseDouble(filtersDTO.getWeight());
                predicates.add(
                        criteriaBuilder.equal(root.get("weight"), weight)
                );
            }
            if (!CollectionUtils.isEmpty(filtersDTO.getMovies())){
                Join<MovieEntity, FigureEntity> join = root.join("movies", JoinType.INNER);
                Expression<String> moviesId = join.get("id");
                predicates.add(moviesId.in(filtersDTO.getMovies()));
            }

            //Remove duplicates
            query.distinct(true);

            //Order resolver
            String orderByField = "name";
            query.orderBy(
                    filtersDTO.isASC() ? criteriaBuilder.asc(root.get(orderByField)) : criteriaBuilder.desc(root.get(orderByField))
            );
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}

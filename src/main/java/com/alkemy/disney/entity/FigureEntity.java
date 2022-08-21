package com.alkemy.disney.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "figure")
@Getter
@Setter
@SQLDelete(sql = "UPDATE figure SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class FigureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String image;
    private String name;
    private Integer age;
    private Double weight;
    private String history;

    private boolean deleted = Boolean.FALSE;

    @ManyToMany(mappedBy = "figures", cascade = CascadeType.REFRESH)
    private List<MovieEntity> movies = new ArrayList<>();
}

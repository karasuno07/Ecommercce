package com.techstore.ecommercce.object.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "brands")
@Getter
@Setter
@ToString
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(columnDefinition = "varchar(50)", nullable = false)
    private String name;

    @ToString.Exclude
    @OneToMany(mappedBy = "brand")
    private List<Product> products;
}

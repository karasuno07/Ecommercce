package com.techstore.ecommercce.object.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@ToString
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(columnDefinition = "varchar(100)", nullable = false)
    private String name;

    @Column(columnDefinition = "varchar(100)", nullable = false)
    private String slug;

    @Column(nullable = false)
    private boolean active = true;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "brand_id", columnDefinition = "bigint", nullable = false)
    private Brand brand;

    @ToString.Exclude
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductDetail> details;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "category_id", columnDefinition = "bigint", nullable = false)
    private Category category;

    @ToString.Exclude
    @OneToMany(mappedBy = "product")
    private List<ProductReview> reviews;
}

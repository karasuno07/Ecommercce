package com.techstore.ecommerce.object.entity.jpa;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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

    @Column(columnDefinition = "varchar(50)", nullable = false)
    private String slug;

    @Column(columnDefinition = "text", nullable = false)
    private String image;

    @ManyToMany
    @JoinTable(name = "categories_brands",
            joinColumns = @JoinColumn(name = "category_id", columnDefinition = "bigint"),
            inverseJoinColumns = @JoinColumn(name = "brand_id", columnDefinition = "bigint"))
    private List<Category> categories;

    @ToString.Exclude
    @OneToMany(mappedBy = "brand")
    private List<Product> products;
}

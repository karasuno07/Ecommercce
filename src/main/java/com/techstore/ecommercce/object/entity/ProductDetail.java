package com.techstore.ecommercce.object.entity;

import com.vladmihalcea.hibernate.type.array.ListArrayType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "product_details")
@TypeDef(name = "list-array", typeClass = ListArrayType.class) //định nghĩa kiểu dữ liệu
@Getter
@Setter
@ToString
public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(nullable = false)
    private int inStock;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private BigDecimal discount;

    @Column(columnDefinition = "varchar(20)", nullable = false)
    private String size;

    @Column(columnDefinition = "varchar(30)", nullable = false)
    private String color;

    @Column(columnDefinition = "jsonb", nullable = false)
    private String descriptions;

    @Type(type = "list-array")
    @Column(columnDefinition = "text[]", nullable = false)
    private List<String> images;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}

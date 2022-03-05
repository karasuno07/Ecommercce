package com.techstore.ecommerce.object.entity.jpa;

import com.techstore.ecommerce.object.model.ProductProperty;
import com.vladmihalcea.hibernate.type.array.ListArrayType;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "product_details")
@TypeDefs({@TypeDef(name = "list-array", typeClass = ListArrayType.class),
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)})
@Getter
@Setter
@ToString
@SequenceGenerator(name = "product_details_id_seq", sequenceName = "product_details_id_seq",
        allocationSize = 1)
public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_details_id_seq")
    private long id;

    @Column(nullable = false)
    private int inStock;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private BigDecimal discount;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb", nullable = false)
    private ProductProperty descriptions;

    private boolean isDefault = false;

    @Type(type = "list-array")
    @Column(columnDefinition = "text[]")
    private List<String> images;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}

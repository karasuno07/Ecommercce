package com.techstore.ecommerce.object.entity.jpa;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "product_reviews")
@Getter
@Setter
@ToString
@SequenceGenerator(name = "product_reviews_id_seq", sequenceName = "product_reviews_id_seq", allocationSize = 1)
public class ProductReview {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_reviews_id_seq")
    private long id;

    @Column(nullable = false)
    private int rating;

    @Column(columnDefinition = "text", nullable = false)
    private String comment;

    @Temporal(TemporalType.TIMESTAMP)
    private Date commentedDate = new Date();

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User user;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}

package com.techstore.ecommercce.object.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "product_reviews")
@Getter
@Setter
@ToString
public class ProductReview {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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
    private Customer customer;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}

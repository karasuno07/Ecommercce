package com.techstore.ecommercce.object.entity;


import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "customers")
@Getter
@Setter
@ToString
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(columnDefinition = "varchar(50)", unique = true)
    private String username;

    @Column(columnDefinition = "varchar(100)")
    private String password;

    @Column(columnDefinition = "varchar(50)")
    private String fullName;

    private boolean gender;

    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Column(columnDefinition = "varchar(12)")
    private String phoneNumber;

    @Column(columnDefinition = "varchar(100)")
    private String email;

    @Column(columnDefinition = "varchar(200)")
    private String address;

    @Column(columnDefinition = "text")
    private String image;

    private boolean active;

    @ToString.Exclude
    @OneToMany(mappedBy = "customer")
    private List<ProductReview> reviews;
}

package com.techstore.ecommerce.object.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "suppliers")
@Getter
@Setter
@ToString
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(columnDefinition = "varchar(100)", nullable = false)
    private String name;

    @Column(columnDefinition = "varchar(200)", nullable = false)
    private String address;

    @Column(columnDefinition = "varchar(12)", nullable = false)
    private String phoneNumber;

    @Column(columnDefinition = "varchar(100)", nullable = false)
    private String email;

    @Column(nullable = false)
    private boolean active = true;

    @ToString.Exclude
    @OneToMany(mappedBy = "supplier")
    private List<Receipt> receipts;
 }

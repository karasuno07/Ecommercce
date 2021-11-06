package com.techstore.ecommerce.object.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Getter
@Setter
@ToString
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(columnDefinition = "text", nullable = false)
    private String orderCode = UUID.randomUUID().toString();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date createdDate = new Date();

    @Column(columnDefinition = "varchar(100)", nullable = false)
    private String recipientName;

    @Column(columnDefinition = "varchar(12)", nullable = false)
    private String phoneNumber;

    @Column(columnDefinition = "varchar(100)", nullable = false)
    private String email;

    @Column(columnDefinition = "varchar(200)", nullable = false)
    private String address;

    @Column(columnDefinition = "varchar(20)", nullable = false)
    private String status;

    @ToString.Exclude
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetail> details;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "customer_id", columnDefinition = "bigint")
    private Customer customer;
}

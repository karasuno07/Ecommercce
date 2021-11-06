package com.techstore.ecommerce.object.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "receipts")
@Getter
@Setter
@ToString
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    //TODO: receipt code

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date createdDate = new Date();

    @ManyToOne
    @JoinColumn( name = "user_id",columnDefinition = "bigint", nullable = false)
    private User user;

    @ToString.Exclude
    @OneToMany(mappedBy = "receipt", cascade = CascadeType.ALL)
    private List<ReceiptDetail> details;

}

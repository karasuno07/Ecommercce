package com.techstore.ecommerce.object.entity.jpa;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "receipts")
@Getter
@Setter
@ToString
@SequenceGenerator(name = "receipts_id_seq", sequenceName = "receipts_id_seq", allocationSize = 1)
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "receipts_id_seq")
    private long id;

    @Column(columnDefinition = "text", nullable = false)
    private String receiptCode = UUID.randomUUID().toString();

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

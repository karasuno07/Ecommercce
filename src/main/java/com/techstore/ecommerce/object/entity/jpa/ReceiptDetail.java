package com.techstore.ecommerce.object.entity.jpa;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "receipt_details")
@Getter
@Setter
@ToString
@SequenceGenerator(name = "receipt_details_id_seq", sequenceName = "receipt_details_id_seq", allocationSize = 1)
public class ReceiptDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "receipt_details_id_seq")
    private long id;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private BigDecimal price;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "receipt_id")
    private Receipt receipt;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    //TODO: xử lí logic, nếu có thì update số lượng sản phẩm này, ko thì phải tạo mới
}

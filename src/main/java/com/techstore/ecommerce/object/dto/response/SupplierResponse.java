package com.techstore.ecommerce.object.dto.response;

import com.techstore.ecommerce.object.entity.Receipt;
import lombok.Data;

import java.util.List;

@Data
public class SupplierResponse {
    private long id;

    private String name;

    private String address;

    private String phoneNumber;

    private String email;

    private List<Receipt> receipts;


}

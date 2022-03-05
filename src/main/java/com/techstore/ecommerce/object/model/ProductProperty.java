package com.techstore.ecommerce.object.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductProperty implements Serializable {

    private String color;
    private String size;
}

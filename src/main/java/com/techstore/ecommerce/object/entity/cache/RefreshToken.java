package com.techstore.ecommerce.object.entity.cache;

import com.techstore.ecommerce.object.entity.jpa.User;
import lombok.Data;

@Data
public class RefreshToken {

    private String token;

    private User user;
}

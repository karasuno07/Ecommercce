package com.techstore.ecommerce.object.wrapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public abstract class AbstractResponse {

    @JsonProperty("status_code")
    private int responseCode = 200;

    private String message;

    private Date timestamp = new Date();

    public AbstractResponse(int responseCode) {
        this.responseCode = responseCode;
    }

    public AbstractResponse(int responseCode, String message) {
        this.responseCode = responseCode;
        this.message = message;
    }
}

package com.techstore.ecommerce.object.wrapper;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SuccessResponse<S> extends AbstractResponse {

    private final S data;

    public SuccessResponse(S data, String message) {
        super(message);
        this.data = data;
    }

    public SuccessResponse(int responseCode, S data, String message) {
        super(responseCode, message);
        this.data = data;
    }

}

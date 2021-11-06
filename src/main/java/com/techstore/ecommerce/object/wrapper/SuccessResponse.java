package com.techstore.ecommerce.object.wrapper;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SuccessResponse<S> extends AbstractResponse {

    private final S data;

    public SuccessResponse(int responseCode, S data) {
        super(responseCode);
        this.data = data;
    }
}

package com.techstore.ecommercce.object.wrapper;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
public class ErrorResponse extends AbstractResponse {

    private Map<String, String> errors;

}

package com.techstore.ecommerce.object.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.techstore.ecommerce.object.model.AuthenticationInfo;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonPropertyOrder({"access_token", "refresh_token", "token_type", "user_info"})
public class TokenResponse {

    @JsonProperty("access_token")
    private String token;

    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonProperty("token_type")
    private String type = "Bearer";

    @JsonProperty("user_info")
    private UserResponse userInfo;

    public TokenResponse(String token,
                         String refreshToken,
                         UserResponse userInfo) {
        this.token = token;
        this.refreshToken = refreshToken;
        this.userInfo = userInfo;
    }
}

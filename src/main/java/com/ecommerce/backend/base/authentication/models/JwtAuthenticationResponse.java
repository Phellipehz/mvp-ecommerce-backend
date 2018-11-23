package com.ecommerce.backend.base.authentication.models;

import java.io.Serializable;

public class JwtAuthenticationResponse implements Serializable {
	
    private String token;
    private Long expiraton;

    public JwtAuthenticationResponse(String token, Long expiraton) {
        this.token = token;
        this.expiraton = expiraton;
    }

    public String getToken() {
        return this.token;
    }
    
}

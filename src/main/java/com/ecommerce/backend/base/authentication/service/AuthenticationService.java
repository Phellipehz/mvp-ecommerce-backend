package com.ecommerce.backend.base.authentication.service;

import javax.servlet.http.HttpServletRequest;

import com.ecommerce.backend.base.authentication.models.JwtAuthenticationResponse;
import com.ecommerce.backend.base.authentication.models.JwtAuthenticationRequest;

public interface AuthenticationService {

	public JwtAuthenticationResponse createTokenBasicAuthentication(JwtAuthenticationRequest authenticationRequest);
	public JwtAuthenticationResponse refreshAuthenticationToken(HttpServletRequest request);
	
}

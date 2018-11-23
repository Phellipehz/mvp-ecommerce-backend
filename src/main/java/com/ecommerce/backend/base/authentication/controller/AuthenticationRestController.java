package com.ecommerce.backend.base.authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.backend.base.authentication.models.JwtAuthenticationRequest;
import com.ecommerce.backend.base.authentication.models.JwtAuthenticationResponse;
import com.ecommerce.backend.base.authentication.service.AuthenticationService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value ="/authentication", produces = "application/json")
public class AuthenticationRestController {

	@Autowired(required=true)
	AuthenticationService authenticationService;
	
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException {
        return ResponseEntity.ok(authenticationService.createTokenBasicAuthentication(authenticationRequest));
    }

    @RequestMapping(path = "/refresh", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
    	JwtAuthenticationResponse response = authenticationService.refreshAuthenticationToken(request);
    	return ResponseEntity.ok(response);
    }

}

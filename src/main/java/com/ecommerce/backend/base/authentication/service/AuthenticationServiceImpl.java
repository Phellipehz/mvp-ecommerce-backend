package com.ecommerce.backend.base.authentication.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.ecommerce.backend.base.authentication.models.JwtAuthenticationResponse;
import com.ecommerce.backend.base.account.model.Account;
import com.ecommerce.backend.base.account.persistence.AccountRepository;
import com.ecommerce.backend.base.account.service.AccountService;
import com.ecommerce.backend.base.authentication.models.JwtAuthenticationRequest;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	@Value("Authorization")
	private String tokenHeader;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	public JwtAuthenticationResponse createTokenBasicAuthentication(JwtAuthenticationRequest authenticationRequest) {

		final Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						authenticationRequest.getEmail(),
						authenticationRequest.getPassword()
						)
				);

		SecurityContextHolder.getContext().setAuthentication(authentication);
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
		final String token = jwtTokenUtil.generateToken(userDetails);
		Long expiration = jwtTokenUtil.getExpirationDateFromToken(token).getTime();
		return new JwtAuthenticationResponse(token, expiration);
	}

		@Override
	public JwtAuthenticationResponse refreshAuthenticationToken(HttpServletRequest request) {
		String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        Account user = (Account) userDetailsService.loadUserByUsername(username);

        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getModificationTime())) {
            String refreshedToken = jwtTokenUtil.refreshToken(token);
            Long expiration = jwtTokenUtil.getExpirationDateFromToken(refreshedToken).getTime();
    		return new JwtAuthenticationResponse(refreshedToken, expiration);
        }
        
        return null;
	}

}

package argedor.security;

import java.io.IOException;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;

import argedor.model.User;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static argedor.security.SecurityConstants.HEADER_STRING;
import static argedor.security.SecurityConstants.EXPIRATION_TIME;
import static argedor.security.SecurityConstants.SECRET;
import static argedor.security.SecurityConstants.TOKEN_PREFIX;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException {

		try {

			User creds = new ObjectMapper().readValue(req.getInputStream(), User.class);

			return authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(creds.getUsername(), creds.getPassword()));

		} catch (IOException e) {
			throw new RuntimeException();

		}
	}

	@Override
	public void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		String authorities = auth.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));
		
		String token = JWT.create()
				.withSubject(((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername())
				.withClaim("ROLE", authorities)
				.withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME)).sign(HMAC512(SECRET.getBytes()));
		// token added the header
		res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);

	}
}

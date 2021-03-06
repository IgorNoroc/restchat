package com.igornoroc.restchat.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.Getter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static com.igornoroc.restchat.config.jwt.JWTAuthenticationFilter.*;

@Getter
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    @Transactional
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(HEADER_STRING);

        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            String username = getSubjectFromToken(token);
            if (username != null) {
                return new UsernamePasswordAuthenticationToken(username, null, getAuthoritiesFromToken(token));
            }
            return null;
        }
        return null;
    }

    private String getSubjectFromToken(String token) {
        return JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                .build()
                .verify(token.replace(TOKEN_PREFIX, ""))
                .getSubject();
    }

    private Collection<GrantedAuthority> getAuthoritiesFromToken(String token) {
        List<String> authorities = JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                .build()
                .verify(token.replace(TOKEN_PREFIX, ""))
                .getClaim("authorities")
                .asList(String.class);

        return authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
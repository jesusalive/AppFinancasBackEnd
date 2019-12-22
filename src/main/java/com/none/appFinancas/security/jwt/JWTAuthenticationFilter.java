package com.none.appFinancas.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.none.appFinancas.dto.LoginFormDTO;
import com.none.appFinancas.dto.UserDTO;
import com.none.appFinancas.entity.User;
import com.none.appFinancas.errors.AuthError;
import com.none.appFinancas.repository.UserRepository;
import com.none.appFinancas.security.UserSS;
import com.none.appFinancas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    private UserRepository userRepository;

    private AuthenticationManager authenticationManager;

    private JWTUtil jwtUtil;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        setAuthenticationFailureHandler(new JWTAuthenticationFailureHandler());
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try{
            LoginFormDTO credentials = new ObjectMapper().readValue(request.getInputStream(), LoginFormDTO.class);

            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    credentials.getUsername(), credentials.getPassword(), new ArrayList<>());

            Authentication auth = authenticationManager.authenticate(authToken);

            return auth;
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        String username = ((UserSS) authResult.getPrincipal()).getUsername();
        String token = jwtUtil.generateToken(username);
        response.addHeader("Authorization", "Bearer " + token);

        Long userId = ((UserSS) authResult.getPrincipal()).getId();
        response.setContentType("application/json");
        response.getWriter().append(json(userId));
    }

    private String json(Long userId) {
        return "{\"userId\": \"" + userId + "\"}";
    }

}

package com.example.foodthought.security;

import com.example.foodthought.dto.auth.LoginUserDto;
import com.example.foodthought.security.jwt.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@Slf4j(topic = "로그인 및 JWT 생성")
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;

    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {
            LoginUserDto requestDto = new ObjectMapper().readValue(request.getInputStream(), com.example.foodthought.dto.auth.LoginUserDto.class);

            // 사용자의 자격 증명을 검증하고, 사용자를 인증하는 과정
            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            requestDto.getUserId(),
                            requestDto.getPassword(),
                            null
                    )
            );
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {
//        String username = ((UserDetailsImpl) authResult.getPrincipal()).getUsername();
//        String token = jwtUtil.createToken(username);
//
//        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, token);
//        response.setStatus(HttpStatus.OK.value());
//        response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");

    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        try {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"status\": 400, \"message\": \"회원을 찾을 수 없습니다.\"}");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
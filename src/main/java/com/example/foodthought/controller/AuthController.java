package com.example.foodthought.controller;

import com.example.foodthought.common.dto.ResponseDto;
import com.example.foodthought.dto.auth.LoginUserDto;
import com.example.foodthought.dto.user.CreateUserDto;
import com.example.foodthought.service.AuthService;
import com.example.foodthought.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    @PostMapping("/login")
    public ResponseEntity<ResponseDto> login(@RequestBody LoginUserDto loginUserDto,
                                             HttpServletResponse response){
        return ResponseEntity.status(HttpStatus.OK).body(authService.login(loginUserDto,response));
    }
    @PostMapping("/logout")
    public ResponseEntity<ResponseDto> logout(HttpServletRequest request,
                                              HttpServletResponse response){
        return ResponseEntity.status(HttpStatus.OK).body(authService.logout(request,response));
    }
}

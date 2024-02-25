package com.example.foodthought.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
@Getter
public class LoginUserDto {
    @NotBlank(message = "아이디는 필수 입력 항목입니다.")
    @Size(max = 50,message = "아이디는 최대 50자 입니다.")
    private String userId;

    @NotBlank(message = "아이디는 필수 입력 항목입니다.")
    @Size(max = 50,message = "아이디는 최대 50자 입니다.")
    private String password;
}

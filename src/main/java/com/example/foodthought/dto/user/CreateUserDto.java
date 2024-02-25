package com.example.foodthought.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateUserDto {
    @NotBlank(message = "아이디는 필수 입력 항목입니다.")
    @Size(max = 50,message = "아이디는 최대 50자 입니다.")
    private String userId;

    @NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
    private String password;

    @NotBlank(message = "회원 이름은 필수 입력 항목입니다.")
    @Size(max = 50,message = "회원 이름은 최대 50자 입니다.")
    private String username;

    private String intro;
}

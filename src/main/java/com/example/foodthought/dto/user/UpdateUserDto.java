package com.example.foodthought.dto.user;

import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdateUserDto {
    @Size(max = 50,message = "회원 이름은 최대 50자 입니다.")
    private String username;

    private String intro;
}

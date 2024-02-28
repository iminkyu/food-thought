package com.example.foodthought.dto.admin;

import com.example.foodthought.entity.User;
import lombok.Getter;

@Getter
public class GetUsersResponseDto {
    private Long id;
    private String userId;
    private String username;
    private String intro;
    private String userPhoto;
    public GetUsersResponseDto(User user) {
        this.id = user.getId();
        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.intro = user.getIntro();
        this.userPhoto = user.getUserPhoto();
    }
}

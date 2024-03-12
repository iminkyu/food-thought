package com.example.foodthought.service;

import com.example.foodthought.common.dto.ResponseDto;
import com.example.foodthought.dto.user.CreateUserDto;
import com.example.foodthought.dto.user.UpdateUserDto;
import com.example.foodthought.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserService {

    /**
     * 책 입력
     * @param createUserDto 유저 생성 시 입력 값
     * @param file 유저 프로필 사진
     * @return 상태 코드 출력 실패는 exception 처리
     */
    public ResponseDto createUser(CreateUserDto createUserDto, MultipartFile file) throws IOException;

    /**
     * 책 입력
     * @param userId 유저 고유 Id
     * @param updateUserDto 업데이트 시 입력받는 유저의 정보
     * @param file 유저 프로필 사진
     */
    public void updateUser(Long userId, UpdateUserDto updateUserDto, MultipartFile file, User user) throws IOException;

    public List<User> findAllUser();

    public User findUser(Long userId);

    public void deleteUser(Long userId);
}

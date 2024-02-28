package com.example.foodthought.service;

import com.example.foodthought.common.dto.ResponseDto;
import com.example.foodthought.dto.user.CreateUserDto;
import com.example.foodthought.dto.user.UpdateUserDto;
import com.example.foodthought.entity.User;
import com.example.foodthought.repository.UserRepository;
import com.example.foodthought.util.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final StorageService storageService;

    @Transactional
    public ResponseDto createUser(CreateUserDto createUserDto, MultipartFile file) throws IOException {
        Optional<User> findUser = userRepository.findByUserId(createUserDto.getUserId());

        if(findUser.isPresent()){
            throw new IllegalArgumentException("이미 존재하는 회원입니다");
        }

        String passwordEncryption = passwordEncoder.encode(createUserDto.getPassword());
        String fileUrl = storageService.uploadFile(file);

        User user = new User(createUserDto,passwordEncryption,fileUrl);
        userRepository.save(user);

        return ResponseDto.success(HttpStatus.CREATED.value(),"회원 가입 성공");
    }

    @Transactional
    public void updateUser(Long userId,UpdateUserDto updateUserDto,MultipartFile file,User user) throws IOException {
        User findUser = userRepository.findById(userId)
                .orElseThrow(()-> new IllegalArgumentException("존재 하지 않은 포스터입니다"));

        if(!findUser.getId().equals(user.getId())){
            throw new IllegalArgumentException("회원 수정은 본인만 가능합니다");
        }

        String fileUrl = storageService.uploadFile(file);

        findUser.updateUser(updateUserDto,fileUrl);
    }

    public List<User> findAllUser(){
        return userRepository.findAll();
    }

    public User findUser(Long userId){
        return userRepository.findById(userId)
                .orElseThrow(()-> new IllegalArgumentException("존재 하지 않은 유저입니다"));
    }

    public void deleteUser(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new IllegalArgumentException("존재 하지 않은 유저입니다"));

        userRepository.delete(user);
    }
}

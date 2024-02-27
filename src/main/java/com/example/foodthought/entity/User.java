package com.example.foodthought.entity;

import com.example.foodthought.dto.user.CreateUserDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,length = 50)
    private String userId;

    @Column
    private String password;

    @Column(length = 50)
    private String username;

    @Column()
    private String intro;

    @Column()
    private String userPhoto;

//    @OneToMany(mappedBy = "follower")
//    private List<Follow> follower;
//
//    @OneToMany(mappedBy = "following")
//    private List<Follow> following;

    public User(CreateUserDto createUserDto, String passwordEncryption, String fileName){
        this.userId = createUserDto.getUserId();
        this.username = createUserDto.getUsername();
        this.password = passwordEncryption;
        this.intro = createUserDto.getIntro();
        this.userPhoto = fileName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(getId(), user.getId()) && Objects.equals(getUsername(), user.getUsername());
    }
}

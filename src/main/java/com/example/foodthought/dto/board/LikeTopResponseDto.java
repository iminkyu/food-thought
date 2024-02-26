package com.example.foodthought.dto.board;

import com.example.foodthought.entity.Board;
import com.example.foodthought.entity.Like;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LikeTopResponseDto {
     private Board board;
     private Long countLikes;
}

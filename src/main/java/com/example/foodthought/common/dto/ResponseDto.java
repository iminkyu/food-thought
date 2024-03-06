package com.example.foodthought.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto {
    /** HTTP 응답 상태 코드 */
    private int status;
    /** 응답 메시지 */
    private String msg;
}

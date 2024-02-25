package com.example.foodthought.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseDto {

    /** HTTP 응답 상태 코드 */
    private int statusCode;
    /** 응답 메시지 */
    private String msg;

}
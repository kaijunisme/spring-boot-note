package com.example.note.dto;

import com.example.note.enumernation.ResponseEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 回應格式
 * @param <T>
 */
@Data
@NoArgsConstructor
public class ResponseDto<T> {

    // 回傳代號
    private int returnCode;

    // 回傳訊息
    private String returnMsg;

    // 回傳資料
    private T data;

    public ResponseDto(ResponseEnum responseEnum) {
        this.returnCode = responseEnum.getReturnCode();
        this.returnMsg = responseEnum.getReturnMsg();
    }

    public ResponseDto(ResponseEnum responseEnum, T data) {
        this.returnCode = responseEnum.getReturnCode();
        this.returnMsg = responseEnum.getReturnMsg();
        this.data = data;
    }

    public static <T> ResponseDto<T> ok() {
        ResponseDto returnDto = new ResponseDto(ResponseEnum.SUCCESS);

        return returnDto;
    }

    public static <T> ResponseDto<T> ok(T data) {
        ResponseDto returnDto = new ResponseDto(ResponseEnum.SUCCESS);
        returnDto.setData(data);

        return returnDto;
    }
    public static <T> ResponseDto<T> error() {
        return new ResponseDto(ResponseEnum.FAILURE);
    }

}

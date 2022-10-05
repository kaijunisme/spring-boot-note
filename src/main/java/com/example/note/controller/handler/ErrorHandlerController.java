package com.example.note.controller.handler;

import com.example.note.dto.ResponseDto;
import com.example.note.enumernation.ResponseEnum;
import com.example.note.error.ServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandlerController {

    /**
     * 處理ServiceException
     * @param e
     * @return
     */
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ResponseDto> ServiceExceptionHandler(ServiceException e) {
        return ResponseEntity.ok(new ResponseDto(e.getResponseEnum()));
    }

    /**
     * 處理上面未列出的例外
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto> ExceptionHandler() {
        return ResponseEntity.ok(new ResponseDto(ResponseEnum.FAILURE));
    }

}

package com.glancebar.mybatis.exceptions;

import com.glancebar.mybatis.utils.ErrResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author YISHEN CAI
 */
@ControllerAdvice
@ResponseBody
public class ExceptionsHandler {


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrResult> handleExceptions(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrResult("内部未知错误", 1, Collections.emptyList()));
    }


    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrResult> handleNotFoundException(NotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrResult("内容未找到", 1, Collections.emptyList()));
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrResult> handleValidationException(MethodArgumentNotValidException e) {
        List<String> details = new ArrayList<>();
        e.getBindingResult().getFieldErrors().forEach(fieldError -> details.add(fieldError.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrResult("参数错误", 1, details));
    }
}

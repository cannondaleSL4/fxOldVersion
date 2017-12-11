package com.dim.fxapp.request.controller;

import com.dim.fxapp.request.exeption.ServerRequestExeption;
import lombok.Data;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * Created by dima on 11.12.17.
 */
@ControllerAdvice
@Order(1)
public class ServerExceptionHandlers {
    @ExceptionHandler(ServerRequestExeption.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse serverError(final ServerRequestExeption ex) {
        return new ErrorResponse("SERVER_NOT_AVAILABLE", "Server of quotes is not available, try again later");
    }

    @Data
    public static class ErrorResponse {
        private final String code;
        private final String message;
    }
}
package com.springproj.schedulebuilder.exception;

import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Builder
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends Throwable {
    public BadRequestException() { super("Bad Request.");}

    public BadRequestException(String exception) { super(exception);}
}

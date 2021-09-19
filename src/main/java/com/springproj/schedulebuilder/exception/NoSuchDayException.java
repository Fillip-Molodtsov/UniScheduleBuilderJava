package com.springproj.schedulebuilder.exception;

import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Builder
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoSuchDayException extends Throwable{
    public NoSuchDayException() {
        super("No day with passed id was found");
    }
}

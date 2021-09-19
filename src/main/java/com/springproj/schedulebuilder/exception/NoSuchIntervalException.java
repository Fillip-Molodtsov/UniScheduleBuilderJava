package com.springproj.schedulebuilder.exception;

import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Builder
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoSuchIntervalException extends Throwable {
    public NoSuchIntervalException() {
        super("No interval with passed id was found");
    }
}

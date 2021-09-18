package com.springproj.schedulebuilder.exception;

import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Builder
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoSuchSubjectException extends Throwable {
    public NoSuchSubjectException() {
        super("No subject with passed id was found");
    }
}

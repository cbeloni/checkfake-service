package br.com.checkfakeservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestMeme extends RuntimeException {

    public BadRequestMeme (String message){
        super(message);
    }
}

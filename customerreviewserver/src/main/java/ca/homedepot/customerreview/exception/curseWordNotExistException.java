package ca.homedepot.customerreview.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class curseWordNotExistException extends RuntimeException
{
    public curseWordNotExistException(String s)
    {
        super(s + " is not currently in the curse word list! ");
    }
}
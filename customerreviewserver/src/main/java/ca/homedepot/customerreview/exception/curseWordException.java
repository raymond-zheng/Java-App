package ca.homedepot.customerreview.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class curseWordException extends RuntimeException
{
    public curseWordException(String s)
    {
        super(s + " is a curse word ! ");
    }
}
    package ca.homedepot.customerreview.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class duplicateCurseWordException extends RuntimeException
{
    public duplicateCurseWordException(String s)
    {
        super(s + " is a already in the curse word list ! ");
    }
}
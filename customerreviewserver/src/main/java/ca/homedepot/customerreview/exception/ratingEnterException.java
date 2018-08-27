  package ca.homedepot.customerreview.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class ratingEnterException extends RuntimeException
{
    public ratingEnterException()
    {
        super("Either enter both ratingTo and ratingFrom or none of them");
    }
}
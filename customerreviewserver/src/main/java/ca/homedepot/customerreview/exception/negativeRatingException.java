package ca.homedepot.customerreview.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class negativeRatingException extends RuntimeException
{
    public negativeRatingException()
    {
        super("Non-negative rating only");
    }
}
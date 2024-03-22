package heliant.controller;

import heliant.exception.ResourceNotFoundException;
import heliant.exception.ResourceNotValidException;
import heliant.exception.TypeNotSupportedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice( assignableTypes = {
        AuthenticationController.class,
        FormularController.class,
        FormularPopunjenController.class,
        PoljeController.class,
        PoljePopunjenoController.class
})
public class AdviceController {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> resourceNotFoundExceptionHandler(
            ResourceNotFoundException resourceNotFoundException
    ) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", resourceNotFoundException.getMessage());
        return errorMap;
    }

    @ExceptionHandler(TypeNotSupportedException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public Map<String, String> typeNotSupportedExceptionHandler(
            TypeNotSupportedException typeNotSupportedException
    ) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", typeNotSupportedException.getMessage());
        return errorMap;
    }

    @ExceptionHandler(ResourceNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public Map<String, String> resourceNotValidExceptionhandler(
            ResourceNotValidException resourceNotValidException
    ) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", resourceNotValidException.getMessage());
        return errorMap;
    }

}

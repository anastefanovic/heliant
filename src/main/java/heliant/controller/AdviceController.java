package heliant.controller;

import heliant.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice( assignableTypes = {
        AuthenticationController.class
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

}

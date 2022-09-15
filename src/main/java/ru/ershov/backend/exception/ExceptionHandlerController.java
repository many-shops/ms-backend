package ru.ershov.backend.exception;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.security.web.csrf.InvalidCsrfTokenException;
import org.springframework.security.web.csrf.MissingCsrfTokenException;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
@Hidden
@RequiredArgsConstructor
public class ExceptionHandlerController {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ValidationErrorResponse onConstraintValidationException(
            ConstraintViolationException e) {
        ValidationErrorResponse error = new ValidationErrorResponse();
        for (ConstraintViolation violation : e.getConstraintViolations()) {
            error.getViolations().add(
                    new Violation(violation.getPropertyPath().toString(), violation.getMessage()));
        }
        return error;
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ValidationErrorResponse onMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {
        ValidationErrorResponse error = new ValidationErrorResponse();
        System.out.println(1);
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            error.getViolations().add(
                    new Violation(fieldError.getField(), fieldError.getDefaultMessage()));
        }
        return error;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ErrorResponseDto onNotFoundException(
            NotFoundException e,
            HttpServletRequest request
    ) {
        return ErrorResponseDto.builder()
                .type(e.getType())
                .message(e.getMessage())
                .url(UrlUtils.buildRequestUrl(request))
                .build();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public ErrorResponseDto accessDeniedExceptionHandler(
            AccessDeniedException e,
            HttpServletRequest request
    ) {
        return ErrorResponseDto.builder()
                .type("access.denied")
                .message(e.getMessage())
                .url(UrlUtils.buildRequestUrl(request))
                .build();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({
            AuthenticationException.class,
            MissingCsrfTokenException.class,
            InvalidCsrfTokenException.class,
            SessionAuthenticationException.class
    })
    public ErrorResponseDto handleAuthenticationException(
            RuntimeException e,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return ErrorResponseDto.builder()
                .type("error.authorization")
                .message(e.getMessage())
                .url(UrlUtils.buildRequestUrl(request))
                .build();
    }
}

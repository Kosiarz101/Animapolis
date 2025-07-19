package org.animapolis.healthcare.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.animapolis.healthcare.exception.BaseClientErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;

@RestControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler(BaseClientErrorException.class)
    public ResponseEntity<ProblemDetail> handleClientException(BaseClientErrorException exception, HttpServletRequest request) {
        ProblemDetail problem = ProblemDetail.forStatus(exception.getStatus());
        problem.setTitle(exception.getStatus().getReasonPhrase());
        problem.setDetail(exception.getLocalizedMessage());
        problem.setInstance(URI.create(request.getRequestURI()));

        exception.printStackTrace();

        return ResponseEntity.status(exception.getStatus()).body(problem);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> handleUnexpected(Exception exception, HttpServletRequest request) {
        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        problem.setTitle("Internal Server Error");
        problem.setDetail("An unexpected error occurred: " + exception.getLocalizedMessage());
        problem.setInstance(URI.create(request.getRequestURI()));

        exception.printStackTrace();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(problem);
    }
}

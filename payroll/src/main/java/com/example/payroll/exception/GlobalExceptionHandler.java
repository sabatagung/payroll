package com.example.payroll.exception;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import com.example.payroll.error.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PayrollAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handlePayrollAlreadyExistsException(PayrollAlreadyExistsException ex) {
        ErrorResponse errorResponse = new ErrorResponse("PAYROLL_ALREADY_EXISTS", "Payroll already exists for this employee for the given month.");
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex) {
        ErrorResponse errorResponse = new ErrorResponse("INTERNAL_SERVER_ERROR", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    @ExceptionHandler(PayrollAlreadyExistsException.class)
//    public ResponseEntity <ErrorResponse> handlePayrollAlreadyExistsException(PayrollAlreadyExistsException ex){
//        ErrorResponse errorResponse = new ErrorResponse() {
//            @Override
//            public HttpStatusCode getStatusCode() {
//                return HttpStatus.CONFLICT;
//            }
//
//            @Override
//            public ProblemDetail getBody() {
//                return ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR) ;
//            }
//        };
//        return null;
//    }

}

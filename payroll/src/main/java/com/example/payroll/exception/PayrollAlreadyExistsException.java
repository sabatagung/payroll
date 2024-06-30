package com.example.payroll.exception;


public class PayrollAlreadyExistsException extends RuntimeException{
    public PayrollAlreadyExistsException (String message){
        super(message);
    }
}

package com.payroll.employee.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException (String entity, String fieldName, String fieldValue){
        super(String.format("%s not found for %s field - %s", entity,fieldName, fieldValue));
    }
}

package org.hlab.ems.dao;

public class EmployeeDBException extends RuntimeException{

    public EmployeeDBException(String message){
        super(message);
    }
    public EmployeeDBException(String message, Throwable cause){
        super(message,cause);
    }
}

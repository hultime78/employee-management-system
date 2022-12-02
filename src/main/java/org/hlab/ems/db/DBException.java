package org.hlab.ems.db;

public class DBException extends RuntimeException{

    public DBException(String message){
        super(message);
    }
    public DBException(String message,Throwable cause){
        super(message,cause);
    }
}

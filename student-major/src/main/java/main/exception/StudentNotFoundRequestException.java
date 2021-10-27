package main.exception;

public class StudentNotFoundRequestException extends RuntimeException{
    public StudentNotFoundRequestException(String message) {
        super(message);
    }

    public StudentNotFoundRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}

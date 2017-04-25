package main.exceptions;

public class DatabaseException extends Exception {
    private String message;

    public DatabaseException(String s) {
        super(s);
        message = s;
    }

    @Override
    public String toString() {
        return message;
    }
}

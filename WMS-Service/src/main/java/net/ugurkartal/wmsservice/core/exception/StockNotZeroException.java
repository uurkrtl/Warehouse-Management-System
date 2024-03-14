package net.ugurkartal.wmsservice.core.exception;

public class StockNotZeroException extends RuntimeException {
    public StockNotZeroException(String message) {
        super(message);
    }
}
package net.ugurkartal.wmsservice.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
public class CustomizedExceptionHandler {

        @ExceptionHandler(RecordNotFoundException.class)
        public ResponseEntity<ErrorResponse> handleRecordNotFoundException(RecordNotFoundException ex, WebRequest request) {
                ErrorResponse errorResponse = new ErrorResponse(
                        request.getDescription(false),
                        HttpStatus.NOT_FOUND,
                        ex.getMessage(),
                        LocalDateTime.now()
                );
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler(DuplicateRecordException.class)
        public ResponseEntity<ErrorResponse> handleDuplicateRecordException(DuplicateRecordException ex, WebRequest request) {
                ErrorResponse errorResponse = new ErrorResponse(
                        request.getDescription(false),
                        HttpStatus.CONFLICT,
                        ex.getMessage(),
                        LocalDateTime.now()
                );
                return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
        }

        @ExceptionHandler(StockNotZeroException.class)
        public ResponseEntity<ErrorResponse> handleStockNotZeroException(StockNotZeroException ex, WebRequest request) {
                ErrorResponse errorResponse = new ErrorResponse(
                        request.getDescription(false),
                        HttpStatus.BAD_REQUEST,
                        ex.getMessage(),
                        LocalDateTime.now()
                );
                return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler
        public ResponseEntity<ErrorResponse>handleMethodArgumentNotValid(MethodArgumentNotValidException ex, WebRequest request){
                StringBuilder errorMessages = new StringBuilder();
                List<ObjectError> errors=ex.getAllErrors();
                errorMessages.append("Error: ");
                for (ObjectError fieldError:errors) {
                        errorMessages.append(fieldError.getDefaultMessage()).append(" - ");
                }
                ErrorResponse errorResponse = new ErrorResponse(
                        request.getDescription(false),
                        HttpStatus.BAD_REQUEST,
                        errorMessages.charAt(errorMessages.length()-2)=='-' ? errorMessages.substring(0,errorMessages.length()-3) : errorMessages.toString(),
                        LocalDateTime.now()
                );
                return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler
        public ResponseEntity<ErrorResponse> handleException(Exception ex, WebRequest request) {
                ErrorResponse errorResponse = new ErrorResponse(
                        request.getDescription(false),
                        HttpStatus.BAD_REQUEST,
                        ex.getMessage(),
                        LocalDateTime.now()
                );
                return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
        }
}
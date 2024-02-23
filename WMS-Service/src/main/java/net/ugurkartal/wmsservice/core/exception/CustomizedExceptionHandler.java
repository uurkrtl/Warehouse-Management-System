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

        @ExceptionHandler
        public ResponseEntity<ErrorResponse>handleMethodArgumentNotValid(MethodArgumentNotValidException ex, WebRequest request){
                List<ObjectError> errors=ex.getAllErrors();
                String errorMessage="Errors: ";
                for (ObjectError fieldError:errors) {
                        errorMessage=errorMessage + " - " + fieldError.getDefaultMessage();
                }
                ErrorResponse errorResponse = new ErrorResponse(
                        request.getDescription(false),
                        HttpStatus.BAD_REQUEST,
                        errorMessage,
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
                return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.BAD_REQUEST);
        }
}
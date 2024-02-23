package net.ugurkartal.wmsservice.core.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private String apiPath;
    private HttpStatus errorCode;
    private String errorMessage;
    private LocalDateTime timestamp;
}
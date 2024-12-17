package inicio.exceptions;

import inicio.service.dto.out.ApiError;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(value = {Exception.class, DuplicatedEntryException.class})
    public ResponseEntity<ApiError> handleGenericException(Exception exception, HttpServletRequest request, HttpServletResponse response) {

        LocalDateTime localDateTime = LocalDateTime.now();

        if (exception instanceof DuplicatedEntryException duplicatedEntryException) {
            return handleDuplicateEntryException(duplicatedEntryException, request, response, localDateTime);
        } else if (exception instanceof NotFoundException notFoundException) {
            return handleNotFoundException(notFoundException, request, response, localDateTime);
        }

        return handleGenericException(exception, request, response, localDateTime);
    }

    private ResponseEntity<ApiError> handleNotFoundException(NotFoundException notFoundException, HttpServletRequest request, HttpServletResponse response, LocalDateTime timestamps) {

        int status = HttpStatus.NOT_FOUND.value();

        ApiError apiError = new ApiError(
                status,
                request.getRequestURL().toString(),
                request.getMethod(),
                "Oops! Not register associated with that value",
                notFoundException.getMessage(),
                timestamps,
                null);

        return ResponseEntity.status(status).body(apiError);
    }

    private ResponseEntity<ApiError> handleDuplicateEntryException(DuplicatedEntryException duplicatedEntryException, HttpServletRequest request, HttpServletResponse response, LocalDateTime timestamps) {
        int status = HttpStatus.BAD_REQUEST.value();

        ApiError apiError = new ApiError(
                status,
                request.getRequestURL().toString(),
                request.getMethod(),
                "Oops! Seems like you already have a contact associated with this information!",
                duplicatedEntryException.getMessage(),
                timestamps,
                null
        );

        return ResponseEntity.status(status).body(apiError);
    }

    private ResponseEntity<ApiError> handleGenericException(Exception exception, HttpServletRequest request, HttpServletResponse response, LocalDateTime timestamps) {
        int httpStatus = HttpStatus.INTERNAL_SERVER_ERROR.value();

        ApiError apiError = new ApiError(
                httpStatus,
                request.getRequestURL().toString(),
                request.getMethod(),
                "Oops!, Something went wrong on our server. Please try again later.",
                exception.getMessage(),
                timestamps,
                null
        );

        return ResponseEntity.status(httpStatus).body(apiError);
    }

}

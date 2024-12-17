package inicio.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@AllArgsConstructor
@Getter
public class NotFoundException extends RuntimeException {

    private final String notFoundValue;
    private final Throwable cause;

    @Override
    public String getMessage() {
        String message = super.getMessage();

        if (message == null) {
            message = "";
        }

        return message.concat("[Data with value: ").concat(notFoundValue).concat(" couldn't be found]");
    }

}

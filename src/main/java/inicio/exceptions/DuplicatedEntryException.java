package inicio.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
@AllArgsConstructor
@Getter
public class DuplicatedEntryException extends RuntimeException {

    private final String duplicatedEntryValue;
    private final Throwable cause;

    @Override
    public String getMessage() {
        String message = super.getMessage();

        if (message == null) {
            message = "";
        }

        return message.concat("[Duplicate entry with value: ").concat(duplicatedEntryValue).concat(" ]");
    }

}

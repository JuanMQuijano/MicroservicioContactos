package inicio.service.dto.out;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ContactDTO(
        @JsonProperty(value = "full_name")
        String fullName,
        String email,
        @JsonProperty(value = "phone_number")
        String phoneNumber) {
}
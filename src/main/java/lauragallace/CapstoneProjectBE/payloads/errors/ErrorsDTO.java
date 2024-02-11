package lauragallace.CapstoneProjectBE.payloads.errors;

import java.time.LocalDateTime;

public record ErrorsDTO(String message, LocalDateTime timestamp) {
}
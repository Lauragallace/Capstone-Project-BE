package lauragallace.CapstoneProjectBE.payloads.users;

import jakarta.validation.constraints.Email;

public record UserLoginDTO(
        @Email(message = "invalid email address")
        String email,
        String password) {
}
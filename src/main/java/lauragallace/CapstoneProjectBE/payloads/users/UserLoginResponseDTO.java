package lauragallace.CapstoneProjectBE.payloads.users;

import lauragallace.CapstoneProjectBE.entities.enums.Role;

public record UserLoginResponseDTO(String token, Role role) {
}
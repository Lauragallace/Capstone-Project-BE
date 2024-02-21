package lauragallace.CapstoneProjectBE.controllers;

import lauragallace.CapstoneProjectBE.entities.User;
import lauragallace.CapstoneProjectBE.exceptions.BadRequestException;
import lauragallace.CapstoneProjectBE.payloads.users.NewUserDTO;
import lauragallace.CapstoneProjectBE.payloads.users.NewUserResponseDTO;
import lauragallace.CapstoneProjectBE.payloads.users.UserLoginDTO;
import lauragallace.CapstoneProjectBE.payloads.users.UserLoginResponseDTO;
import lauragallace.CapstoneProjectBE.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED) // status code 201
    public NewUserResponseDTO createUser(@RequestBody @Validated NewUserDTO body, BindingResult validation) {
        System.out.println(validation);
        if (validation.hasErrors()) {
            System.out.println(validation.getAllErrors());
            throw new BadRequestException("Errors in payload!");
        } else {
            User newUser = authService.save(body);
            return new NewUserResponseDTO(newUser.getId());
        }
    }
    @PostMapping("/login")
    public UserLoginResponseDTO login(@RequestBody @Validated UserLoginDTO body) {
        String accessToken = this.authService.authenticateUser(body);
        User user = this.authService.findByEmail(body.email());
        return new UserLoginResponseDTO(
                accessToken,user.getRole()
        );
    }
}
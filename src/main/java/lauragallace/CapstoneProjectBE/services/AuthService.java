package lauragallace.CapstoneProjectBE.services;

import lauragallace.CapstoneProjectBE.entities.User;
import lauragallace.CapstoneProjectBE.entities.enums.Role;
import lauragallace.CapstoneProjectBE.exceptions.BadRequestException;
import lauragallace.CapstoneProjectBE.exceptions.UnauthorizedException;
import lauragallace.CapstoneProjectBE.payloads.users.NewUserDTO;
import lauragallace.CapstoneProjectBE.payloads.users.UserLoginDTO;
import lauragallace.CapstoneProjectBE.repositories.UsersRepository;
import lauragallace.CapstoneProjectBE.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private UsersService usersService;
    @Autowired
    private PasswordEncoder bcrypt;
    @Autowired
    private JWTTools jwtTools;

    public User save(NewUserDTO body) {
        this.usersRepository.findByEmail(body.email()).ifPresent(user -> {
            throw new BadRequestException("The e-mail " + user.getEmail() + " is already in use!");
        });
        User newUser = new User();
        newUser.setFirstName(body.firstName());
        newUser.setLastName(body.lastName());
        newUser.setEmail(body.email());
        newUser.setPassword(bcrypt.encode(body.password()));
        newUser.setAvatarUrl("https://ui-avatars.com/api/?name=" + body.firstName() + "+" + body.lastName());
        if (body.role() == null) {
            newUser.setRole(Role.CUSTOMER);
        } else if (body.role().equalsIgnoreCase("ADMIN")) {
            newUser.setRole(Role.ADMIN);
        } else {
            newUser.setRole(Role.CUSTOMER);
        }
        return this.usersRepository.save(newUser);
    }
    public User findByEmail(String email){
        return this.usersService.findByEmail(email);
    }
    public String authenticateUser(UserLoginDTO body) {
        User user = this.findByEmail(body.email());
        if (bcrypt.matches(body.password(), user.getPassword())) {
            return this.jwtTools.createToken(user);
        } else {
            throw new UnauthorizedException("Wrong email or password");
        }
    }
}
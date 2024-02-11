package lauragallace.CapstoneProjectBE.controllers;

import lauragallace.CapstoneProjectBE.entities.User;
import lauragallace.CapstoneProjectBE.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UsersService usersService;
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<User> getAllUsers(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size,
                                  @RequestParam(defaultValue = "lastName") String orderBy){
        return this.usersService.getAllUsers(page, size, orderBy);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User getUserById(@PathVariable UUID id){
        return this.usersService.findById(id);
    }
    @GetMapping("/me")
    public User getCurrentUser(@AuthenticationPrincipal User currentUser){
        return currentUser;
    }

}
package lauragallace.CapstoneProjectBE.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lauragallace.CapstoneProjectBE.entities.User;
import lauragallace.CapstoneProjectBE.exceptions.NotFoundException;
import lauragallace.CapstoneProjectBE.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

    public Page<User> getAllUsers(int page, int size, String orderBy) {
        if (size >= 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return usersRepository.findAll(pageable);
    }
    public User findById(UUID id) {
        return this.usersRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }
    public User findByEmail(String email) throws NotFoundException {
        return this.usersRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("No user found for email " + email));
    }
}

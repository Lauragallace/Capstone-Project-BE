package lauragallace.CapstoneProjectBE.entities;

import jakarta.persistence.OneToMany;
import lauragallace.CapstoneProjectBE.entities.enums.Role;
import org.springframework.security.access.event.LoggerListener;

import java.util.List;

public class Customer extends User{
    public Customer (){
        super.setRole(Role.CUSTOMER);
    }
    @OneToMany (mappedBy = "customer")
    private List<Reservation> reservationList;
}

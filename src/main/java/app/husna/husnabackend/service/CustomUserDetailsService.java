package app.husna.husnabackend.service;

import app.husna.husnabackend.repository.OrganizationRepository;
import app.husna.husnabackend.repository.ParentRepository;
import app.husna.husnabackend.repository.StaffRepository;
import app.husna.husnabackend.repository.StudentRepository;
import app.husna.husnabackend.security.UserDetailsBase;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final OrganizationRepository organizationRepository;
    private final StaffRepository staffRepository;
    private final ParentRepository parentRepository;

    public CustomUserDetailsService(OrganizationRepository organizationRepository,
                                    StaffRepository staffRepository,
                                    ParentRepository parentRepository) {
        this.organizationRepository = organizationRepository;
        this.staffRepository = staffRepository;
        this.parentRepository = parentRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<? extends UserDetailsBase> user = organizationRepository.findByEmail(email);

        if (user.isEmpty()) {
            user = staffRepository.findStaffByEmail(email);
        }
        if (user.isEmpty()) {
            user = parentRepository.findParentByEmail(email);
        }

        return user.orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }
}

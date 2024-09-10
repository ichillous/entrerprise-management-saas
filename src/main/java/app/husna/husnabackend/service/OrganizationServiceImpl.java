package app.husna.husnabackend.service;

import app.husna.husnabackend.model.Organization;
import app.husna.husnabackend.model.Role;
import app.husna.husnabackend.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class OrganizationServiceImpl implements OrganizationService {
    private final OrganizationRepository organizationRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public OrganizationServiceImpl(OrganizationRepository organizationRepository, PasswordEncoder passwordEncoder) {
        this.organizationRepository = organizationRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Organization registerOrganization(Organization organization) {
        organization.setPassword(passwordEncoder.encode(organization.getPassword()));
        organization.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        return organizationRepository.save(organization);
    }

    @Override
    public Organization createOrganization(Organization organization) {
        organization.setAdminRole(Role.ORG_ADMIN);
        return registerOrganization(organization);
    }


    @Override
    public Organization findOrganizationById(Long id) {
        return organizationRepository.findOrganizationById(id);
    }

    @Override
    public void setAdminRole(Organization organization, Role role) {
        organization.setAdminRole(role);
        organizationRepository.save(organization);
    }

    @Override
    public List<Organization> getAllOrganizations() {
        return organizationRepository.findAll();
    }

    @Override
    public Organization updateOrganization(Organization organization) {
        return organizationRepository.save(organization);
    }

    @Override
    public ResponseEntity<Void> deleteOrganization(Long id) {
        organizationRepository.deleteById(id);
        return null;
    }

    @Override
    public Optional<Organization>  findOrganizationByEmail(String email) {
        return organizationRepository.findByEmail(email);
    }

    @Override
    public Optional<Organization> findOrganizationByCity(String city) {
        return organizationRepository.getOrganizationByCity(city);
    }

    @Override
    public Optional<Organization> findOrganizationByState(String state) {
        return organizationRepository.getOrganizationByState(state);
    }

    @Override
    public Optional<Organization> findOrganizationByName(String name) {
        return organizationRepository.getOrganizationByName(name);
    }

    @Override
    public List<Organization> findAllByOrderByIdDesc() {
        return organizationRepository.findAllByOrderByIdDesc();
    }

    @Override
    public List<Organization> findAllOrganizationsOrderedByNameAsc() {
        return organizationRepository.findAllByOrderByNameAsc();
    }
    @Override
    public Optional<Organization> findByEmail(String email) {
        return organizationRepository.findByEmail(email);
    }

    @Override
    public boolean existsByEmail(String email) {
        return organizationRepository.existsByEmail(email);
    }
}

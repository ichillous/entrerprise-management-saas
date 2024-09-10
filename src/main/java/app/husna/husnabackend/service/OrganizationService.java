package app.husna.husnabackend.service;

import app.husna.husnabackend.model.Organization;
import app.husna.husnabackend.model.Role;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;



public interface OrganizationService {
    Organization createOrganization(Organization organization);
    Organization findOrganizationById(Long id);
    Optional<Organization> findByEmail(String email);
    void setAdminRole(Organization organization, Role role);
    List<Organization> getAllOrganizations();
    Organization updateOrganization(Organization organization);
    ResponseEntity<Void> deleteOrganization(Long id);
    Organization registerOrganization(Organization organization);
    Optional<Organization> findOrganizationByEmail(String email);
    Optional<Organization> findOrganizationByCity(String city);
    Optional<Organization> findOrganizationByState(String state);
    Optional<Organization> findOrganizationByName(String name);
    List<Organization> findAllByOrderByIdDesc();
    List<Organization> findAllOrganizationsOrderedByNameAsc();
    boolean existsByEmail(String email);
}

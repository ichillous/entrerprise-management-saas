package app.husna.husnabackend.repository;

import app.husna.husnabackend.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    Organization findOrganizationById(Long id);
    Optional<Organization> findByEmail(String email);
    Optional<Organization> getOrganizationByCity(String city);
    Optional<Organization> getOrganizationByState(String state);
    Optional<Organization> getOrganizationByName(String name);
    List<Organization> findAllByOrderByIdDesc();
    List<Organization> findAllByOrderByNameAsc();
    boolean existsByEmail(String email);
}

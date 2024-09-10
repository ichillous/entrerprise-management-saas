package app.husna.husnabackend.repository;

import app.husna.husnabackend.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
    Optional<Staff> findStaffById(long id);
    Optional<Staff> findStaffByEmail(String email);
    Optional<Staff> findStaffByName(String name);
    List<Staff> findAllByOrderByNameAsc();
    boolean existsStaffByEmail(String email);
}

package app.husna.husnabackend.repository;

import app.husna.husnabackend.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmail(String email);
    Optional<Student> findByName(String name);
    List<Student> findAllByOrderByNameAsc();
    List<Student> findAllByOrderByEnrollmentDateDesc();
    boolean existsByEmail(String email);
}

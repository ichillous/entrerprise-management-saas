package app.husna.husnabackend.repository;

import app.husna.husnabackend.model.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParentRepository extends JpaRepository<Parent, Long> {
    Optional<Parent> findParentByName(String name);
    Optional<Parent> findParentByEmail(String email);
    Optional<Parent> findParentById(Long id);
    void deleteParentById(Long id);
    List<Parent> findAllByOrderByNameAsc();

}

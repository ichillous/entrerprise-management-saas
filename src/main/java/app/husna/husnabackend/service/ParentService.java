package app.husna.husnabackend.service;

import app.husna.husnabackend.model.Parent;

import java.util.List;
import java.util.Optional;

public interface ParentService {
    Parent createParent(Parent parent);
    Optional<Parent> findParentByName(String name);
    Optional<Parent> findParentByEmail(String email);
    Optional<Parent> findParentById(Long id);
    List<Parent> findAllParents();
    void deleteParentById(Long id);
    List<Parent> findAllByOrderByNameAsc();


}

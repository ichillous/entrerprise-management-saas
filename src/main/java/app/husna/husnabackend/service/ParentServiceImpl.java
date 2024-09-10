package app.husna.husnabackend.service;

import app.husna.husnabackend.model.Parent;
import app.husna.husnabackend.model.Role;
import app.husna.husnabackend.repository.ParentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParentServiceImpl implements ParentService {
    private final ParentRepository parentRepository;

    @Autowired
    public ParentServiceImpl(ParentRepository parentRepository) {
        this.parentRepository = parentRepository;
    }

    @Override
    public Parent createParent(Parent parent) {
        parent.setRole(Role.PARENT);
        return parentRepository.save(parent);
    }

    @Override
    public Optional<Parent> findParentByName(String name) {
        return parentRepository.findParentByName(name);
    }

    @Override
    public Optional<Parent> findParentByEmail(String email) {
        return parentRepository.findParentByEmail(email);
    }

    @Override
    public Optional<Parent> findParentById(Long id) {
        return parentRepository.findParentById(id);
    }

    @Override
    public List<Parent> findAllParents() {
        return parentRepository.findAll();
    }

    @Override
    public void deleteParentById(Long id) {
        parentRepository.deleteParentById(id);
    }

    @Override
    public List<Parent> findAllByOrderByNameAsc() {
        return parentRepository.findAllByOrderByNameAsc();
    }

}

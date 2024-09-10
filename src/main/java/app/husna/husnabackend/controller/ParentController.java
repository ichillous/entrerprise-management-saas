package app.husna.husnabackend.controller;

import app.husna.husnabackend.model.Parent;
import app.husna.husnabackend.model.Role;
import app.husna.husnabackend.service.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parents")
public class ParentController {
    private final ParentService parentService;

    @Autowired
    public ParentController(ParentService parentService) {
        this.parentService = parentService;
    }

    @PostMapping
    public ResponseEntity<Parent> createParent(@RequestBody Parent parent) {
        parent.setRole(Role.PARENT);
        return ResponseEntity.ok(parentService.createParent(parent));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Parent> findParentById(@PathVariable Long id) {
        return parentService.findParentById(id)
                .map(parent -> new ResponseEntity<>(parent, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Parent> findParentByName(@PathVariable String name) {
        return parentService.findParentByName(name)
                .map(parent -> new ResponseEntity<>(parent, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/name/{email}")
    public ResponseEntity<Parent> findParentByEmail(@PathVariable String email) {
        return parentService.findParentByEmail(email)
                .map(parent -> new ResponseEntity<>(parent, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Parent>> findAllParents() {
        List<Parent> parents = parentService.findAllParents();
        return new ResponseEntity<>(parents, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParent(@PathVariable Long id) {
        parentService.deleteParentById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/ordered-by-name")
    public ResponseEntity<List<Parent>> findAllParentsOrderedByName() {
        List<Parent> parents = parentService.findAllByOrderByNameAsc();
        return new ResponseEntity<>(parents, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Parent> updateParent(@PathVariable Long id, @RequestBody Parent parent) {
        return parentService.findParentById(id)
                .map(existingParent -> {
                    parent.setId(id);
                    Parent updatedParent = parentService.createParent(parent);
                    return new ResponseEntity<>(updatedParent, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}

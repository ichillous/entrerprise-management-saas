package app.husna.husnabackend.controller;

import app.husna.husnabackend.model.Organization;
import app.husna.husnabackend.model.Role;
import app.husna.husnabackend.service.OrganizationService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/organizations")
public class OrganizationController {

    private final OrganizationService organizationService;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @PostMapping
    public ResponseEntity<Organization> createOrganization(@RequestBody Organization organization) {
        organization.setAdminRole(Role.ORG_ADMIN);
        return ResponseEntity.ok(organizationService.createOrganization(organization));
    }

    @GetMapping("/{id}")
    public Organization findOrganizationById(@PathVariable Long id) {
        return organizationService.findOrganizationById(id);
    }

    @GetMapping
    public ResponseEntity<List<Organization>> getAllOrganizations() {
        List<Organization> organizations = organizationService.getAllOrganizations();
        return new ResponseEntity<>(organizations, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Organization> updateOrganization(@PathVariable Long id, @RequestBody Organization organization) {
       organization = ResponseEntity.ok(organizationService.findOrganizationById(id)).getBody();
       return ResponseEntity.ok(organizationService.updateOrganization(organization));
    }

    @PutMapping("/{id}/role")
    public ResponseEntity<Organization> updateAdminRole(@PathVariable Long id, @RequestBody Role role) {
        Organization organization = organizationService.findOrganizationById(id);
        if (organization == null) {
            return ResponseEntity.notFound().build();
        }
        organizationService.setAdminRole(organization, role);
        return ResponseEntity.ok(organization);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrganization(@PathVariable Long id) {
        return organizationService.deleteOrganization(id);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Organization> getOrganizationByEmail(@PathVariable String email) {
        return organizationService.findOrganizationByEmail(email)
                .map(organization -> new ResponseEntity<>(organization, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<Organization> getOrganizationByCity(@PathVariable String city) {
        return organizationService.findOrganizationByCity(city)
                .map(organization -> new ResponseEntity<>(organization, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/state/{state}")
    public ResponseEntity<Organization> getOrganizationByState(@PathVariable String state) {
        return organizationService.findOrganizationByState(state)
                .map(organization -> new ResponseEntity<>(organization, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Organization> getOrganizationByName(@PathVariable String name) {
        return organizationService.findOrganizationByName(name)
                .map(organization -> new ResponseEntity<>(organization, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/ordered-by-id-desc")
    public ResponseEntity<List<Organization>> getAllOrganizationsOrderedByIdDesc() {
        List<Organization> organizations = organizationService.findAllByOrderByIdDesc();
        return new ResponseEntity<>(organizations, HttpStatus.OK);
    }

    @GetMapping("/ordered-by-name-asc")
    public ResponseEntity<List<Organization>> getAllOrganizationsOrderedByNameAsc() {
        List<Organization> organizations = organizationService.findAllOrganizationsOrderedByNameAsc();
        return new ResponseEntity<>(organizations, HttpStatus.OK);
    }

    @GetMapping("/exists/email/{email}")
    public ResponseEntity<Boolean> existsByEmail(@PathVariable String email) {
        boolean exists = organizationService.existsByEmail(email);
        return new ResponseEntity<>(exists, HttpStatus.OK);
    }
}

package app.husna.husnabackend.controller;

import app.husna.husnabackend.model.Staff;
import app.husna.husnabackend.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/staff")
public class StaffController {
    private final StaffService staffService;

    @Autowired
    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @PostMapping
    public ResponseEntity<Staff> createStaff(@RequestBody Staff staff) {
        Staff savedStaff = staffService.saveStaff(staff);
        return new ResponseEntity<>(savedStaff, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Staff> findStaffById(@PathVariable Long id) {
        return staffService.findStaffById(id)
                .map(staff -> new ResponseEntity<>(staff, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Staff>> findAllStaff() {
        List<Staff> allStaff = staffService.findAllStaff();
        return new ResponseEntity<>(allStaff, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Staff> updateStaff(@PathVariable Long id, @RequestBody Staff staff) {
        return staffService.findStaffById(id)
                .map(existingStaff -> {
                    staff.setId(id);
                    Staff updatedStaff = staffService.updateStaff(staff);
                    return new ResponseEntity<>(updatedStaff, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStaff(@PathVariable Long id) {
        return staffService.findStaffById(id)
                .map(staff -> {
                    staffService.deleteStaff(id);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Staff> findStaffByName(@PathVariable String name) {
        return staffService.findStaffByName(name)
                .map(staff -> new ResponseEntity<>(staff, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Staff> findStaffByEmail(@PathVariable String email) {
        return staffService.findStaffByEmail(email)
                .map(staff -> new ResponseEntity<>(staff, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/ordered-by-name")
    public ResponseEntity<List<Staff>> findAllOrderByNameAsc() {
        List<Staff> orderedStaff = staffService.findAllOrderByNameAsc();
        return new ResponseEntity<>(orderedStaff, HttpStatus.OK);
    }

    @GetMapping("/exists/email/{email}")
    public ResponseEntity<Boolean> existsStaffByEmail(@PathVariable String email) {
        boolean exists = staffService.existsStaffByEmail(email);
        return new ResponseEntity<>(exists, HttpStatus.OK);
    }
}

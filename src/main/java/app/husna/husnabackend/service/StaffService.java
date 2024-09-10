package app.husna.husnabackend.service;

import app.husna.husnabackend.model.Role;
import app.husna.husnabackend.model.Staff;

import java.util.List;
import java.util.Optional;

public interface StaffService {
    Staff saveStaff(Staff staff);
    Optional<Staff> findStaffById(Long id);
    void updateRole(Staff staff, Role role);
    List<Staff> findAllStaff();
    Staff updateStaff(Staff staff);
    void deleteStaff(Long id);
    Optional<Staff> findStaffByName(String name);
    Optional<Staff> findStaffByEmail(String email);
    List<Staff> findAllOrderByNameAsc();
    boolean existsStaffByEmail(String email);
}

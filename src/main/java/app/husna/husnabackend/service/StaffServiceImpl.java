package app.husna.husnabackend.service;

import app.husna.husnabackend.model.Role;
import app.husna.husnabackend.model.Staff;
import app.husna.husnabackend.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StaffServiceImpl implements StaffService {
    private final StaffRepository staffRepository;

    @Autowired
    public StaffServiceImpl(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @Override
    public Staff saveStaff(Staff staff) {
        if (staff.getRole() == null) {
            staff.setRole(Role.TEACHER);
        }
        return staffRepository.save(staff);
    }

    @Override
    public Optional<Staff> findStaffById(Long id) {
        return staffRepository.findStaffById(id);
    }

    @Override
    public void updateRole(Staff staff, Role role) {
        staff.setRole(role);
        staffRepository.save(staff);
    }

    @Override
    public List<Staff> findAllStaff() {
        return staffRepository.findAll();
    }

    @Override
    public Staff updateStaff(Staff staff) {
        return staffRepository.save(staff);
    }

    @Override
    public void deleteStaff(Long id) {
        staffRepository.deleteById(id);
    }

    @Override
    public Optional<Staff> findStaffByName(String name) {
        return staffRepository.findStaffByName(name);
    }

    @Override
    public Optional<Staff> findStaffByEmail(String email) {
        return staffRepository.findStaffByEmail(email);
    }

    @Override
    public List<Staff> findAllOrderByNameAsc(){
        return staffRepository.findAllByOrderByNameAsc();
    }

    @Override
    public boolean existsStaffByEmail(String email) {
        return staffRepository.existsStaffByEmail(email);
    }
}

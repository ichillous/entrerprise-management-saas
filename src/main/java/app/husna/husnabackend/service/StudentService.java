package app.husna.husnabackend.service;

import app.husna.husnabackend.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    Student createStudent(Student student);
    Optional<Student> getStudentById(Long id);
    Optional<Student> getStudentByEmail(String email);
    Optional<Student> getStudentByName(String name);
    List<Student> getAllStudents();
    List<Student> getAllStudentsOrderedByNameAsc();
    List<Student> getAllStudentsOrderedByEnrollmentDateDesc();
    Student updateStudent(Student student);
    void deleteStudentById(Long id);
    boolean existsByEmail(String email);
}

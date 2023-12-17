package ru.hogwarts.school2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school2.model.Faculty;
import ru.hogwarts.school2.model.Student;
import ru.hogwarts.school2.repository.StudentRepository;

import java.util.Collection;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
@Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    @Override
    public Collection<Student> findStudentsByAgeBetween(int min, int max) {
        return studentRepository.findByAgeBetween(min, max);
    }

    public Collection<Student> getStudentsByFaculty(Faculty faculty) {
        return faculty.getStudents();
    }

    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }
    @Override
    public Student findStudent(Long id) {
        return studentRepository.findById(id).orElse(null);
    }
    @Override
    public Student editStudent(Long id, Student student) {
        return studentRepository.save(student);
    }
    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Collection<Student> getAllStudents() {
        return null;
    }
}
package ru.hogwarts.school2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school2.model.Faculty;
import ru.hogwarts.school2.model.Student;

import java.util.Collection;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Collection<Student> findByAgeBetween(int min, int max);

    Collection<Student> findByFaculty(Faculty faculty);
}
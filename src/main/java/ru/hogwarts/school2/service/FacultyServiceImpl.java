package ru.hogwarts.school2.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school2.model.Faculty;
import ru.hogwarts.school2.model.Student;
import ru.hogwarts.school2.repository.FacultyRepository;

import java.util.Collection;
import java.util.Optional;

@Service
public class FacultyServiceImpl implements FacultyService {
    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty addFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);

    }
    @Override
    public Collection<Faculty> findFacultiesByNameOrColor(String name, String color) {
        if (!name.isEmpty()) {
            return facultyRepository.findByNameIgnoreCase(name);
        } else {
            return facultyRepository.findByColorIgnoreCase(color);
        }
    }

    @Override
    public Faculty getFacultyByStudent(Student student) {
        return student.getFaculty();
    }

    public Faculty findFaculty(Long id) {
        return facultyRepository.findById(id).orElse(null);
    }

    public Faculty editFaculty(Long id, Faculty faculty) {
        Optional<Faculty> optionalFaculty = facultyRepository.findById(id);
        if (optionalFaculty.isPresent()) {
            Faculty foundFaculty = optionalFaculty.get();
            foundFaculty.setName(faculty.getName());
            foundFaculty.setColor(faculty.getColor());
            return facultyRepository.save(foundFaculty);
        } else {
            return null;
        }
    }
    public void deleteFaculty(Long id) {
        facultyRepository.deleteById(id);
    }
}
package ru.hogwarts.school2.service;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import ru.hogwarts.school2.model.Faculty;
import ru.hogwarts.school2.model.Student;
import ru.hogwarts.school2.repository.FacultyRepository;

import java.util.Collection;

@Service
public class FacultyServiceImpl implements FacultyService {
    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }
    @Override
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

    @Override
    public Faculty findFaculty(Long id) {
        return facultyRepository.findById(id).orElse(null);
    }
    @Override
    public Faculty editFaculty(Long id, Faculty faculty) {
        return facultyRepository.findById(id)
                .map(foundFaculty -> {
                    foundFaculty.setName(faculty.getName());
                    foundFaculty.setColor(faculty.getColor());
                    return facultyRepository.save(foundFaculty);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Faculty not found with id: " + id));
    }
    @Override
    public void deleteFaculty(Long id) {
        facultyRepository.deleteById(id);
    }
}
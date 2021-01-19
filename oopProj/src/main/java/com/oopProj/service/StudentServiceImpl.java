package com.oopProj.service;

import com.oopProj.models.Student;
import com.oopProj.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepo;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepo) {
        this.studentRepo = studentRepo;
    }

    @Override
    public Optional<Student> getStudent(Integer studentId) {
        return studentRepo.findById(studentId);
    }

    @Override
    public Student setStudent(Student student) {
        Student studentToSave = null;
        if (student.getStudentId() != null) {
            studentToSave = student;
        } else {
            studentToSave = new Student(student.getName(), student.getSurname(), student.getIndexNum(),
                    student.getEmail(), student.isLandline());
        }
        return studentRepo.save(studentToSave);
    }

    @Override
    public void deleteStudent(Integer studentId) {
        studentRepo.deleteById(studentId);
    }

    @Override
    public Page<Student> getStudents(Pageable pageable) {
        return studentRepo.findAll(pageable);
    }
}

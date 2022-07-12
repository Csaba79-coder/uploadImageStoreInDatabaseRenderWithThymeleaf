package com.csaba79coder.uploadimagestoreindatabaserenderwiththymeleaf.service;

import com.csaba79coder.uploadimagestoreindatabaserenderwiththymeleaf.model.Student;
import com.csaba79coder.uploadimagestoreindatabaserenderwiththymeleaf.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository repository;

    public void createStudent(Student student) {
        repository.save(student);

    }
    public List<Student> getAllStudent(){
        return repository.findAll();

    }

    public Optional<Student> findStudentById(long id){
        return repository.findById(id);
    }
}


package com.csaba79coder.uploadimagestoreindatabaserenderwiththymeleaf.repository;

import com.csaba79coder.uploadimagestoreindatabaserenderwiththymeleaf.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}

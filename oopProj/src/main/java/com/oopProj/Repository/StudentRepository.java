package com.oopProj.Repository;

import com.oopProj.Models.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    Optional<Student> findByIndexNum(String indexNum);

    Page<Student> findByIndexNumStartsWith(String indexNum, Pageable pageable);

    Page<Student> findBySurnameStartsWithIgnoreCase(String surname, Pageable pageable);
}

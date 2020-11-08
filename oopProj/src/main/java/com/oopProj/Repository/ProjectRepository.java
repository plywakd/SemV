package com.oopProj.Repository;

import com.oopProj.Models.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

        Page<Project> findByNameContainingIgnoreCase(String name, Pageable pageable);

        List<Project> findByNameContainingIgnoreCase(String name);

        void deleteById(Long projectId);

        Optional<Project> findById(Long projectId);
}

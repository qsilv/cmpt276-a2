package com.cmpt.a2.models;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {
    List<Student> findByHeight(double height);
    List<Student> findByName(String name);
}

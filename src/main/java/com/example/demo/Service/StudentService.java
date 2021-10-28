package com.example.demo.Service;

import com.example.demo.Entiry.Student;

import java.util.List;

public interface StudentService {

    List<Student> findAll();
    Student findById(Integer id);
    Student save(Student student);
    Student update(Student student);
    void delete(Integer id);
}

package com.example.demo.Controller;

import com.example.demo.Entiry.Student;
import com.example.demo.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    StudentService studentService;


    @GetMapping
    public ResponseEntity<List<Student>> findAll(){
        return  new ResponseEntity<>(studentService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> findById(@PathVariable Integer id){
        return  new ResponseEntity<>(studentService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Student> save(@RequestBody Student student){

        return new ResponseEntity<>(studentService.save(student), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Student> update(@RequestBody Student student){

        return new ResponseEntity<>(studentService.update(student), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity update(@PathVariable Integer id){
        studentService.delete(id);
        return new ResponseEntity<>( HttpStatus.OK);
    }


}

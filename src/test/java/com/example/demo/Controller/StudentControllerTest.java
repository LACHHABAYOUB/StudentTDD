package com.example.demo.Controller;

import com.example.demo.Entiry.Student;
import com.example.demo.Service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest
class StudentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    StudentService studentService;

    Student student1, student2;

    @BeforeEach
    void setup(){
        student1 = Student.builder().id(1).name("Esayas").gpa(3.5).build();
        student2 = Student.builder().name("Samuel").gpa(3.5).build();

    }


    @Test
    void findAll() throws Exception {
        List<Student> studentList = Arrays.asList(student1,student2);
        Mockito.when(studentService.findAll()).thenReturn(List.of(student1,student2));

        mockMvc.perform(
                MockMvcRequestBuilders.get("/students")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(studentList)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();



    }

    @Test
    void findById() throws Exception {

        Mockito.when(studentService.findById(student1.getId())).thenReturn(student1);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/students/"+ student1.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(student1)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();


    }


    @Test
    void testSave() throws Exception {

        Mockito.when(studentService.save(student1)).thenReturn(student1);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student1))
        ).andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(student1)))
                .andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();


    }

    @Test
    void testUpdate() throws Exception {

        Mockito.when(studentService.update(student1)).thenReturn(student1);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student1))
        ).andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(student1)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();


    }

    @Test
    void testDeleteById() throws Exception {



        mockMvc.perform(
                MockMvcRequestBuilders.delete("/students/"+student1.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student1))
        ).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();


    }


}
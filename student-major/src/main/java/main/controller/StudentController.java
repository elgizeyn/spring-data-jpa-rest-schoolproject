package main.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import main.dto.StudentDto;
import main.entity.StudentEntity;
import main.entity.TeacherEntity;
import main.exception.StudentNotFoundRequestException;
import main.serviceinter.TeacherServiceInter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import main.serviceinter.StudentServiceInter;
import javax.validation.Valid;
import java.util.*;

@RestController
@RequiredArgsConstructor
@Log
public class StudentController {

    private final StudentServiceInter studentServiceInter;
    private final TeacherServiceInter teacherServiceInter;

    @GetMapping("/get/{name}") //+
    public ResponseEntity<?> get(@PathVariable String name){
        Optional<StudentEntity> studentEntity = Optional.ofNullable(studentServiceInter.getByStudentName(name));
        if(!studentEntity.isPresent()){
            throw new StudentNotFoundRequestException("No student with the given name");
        }
        return new ResponseEntity<StudentEntity>(studentEntity.get(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}") //+
    public ResponseEntity<?> delete(@PathVariable Integer id){
        log.info("start delete method");
        studentServiceInter.deleteById(id);
        log.info("end of delete method");
        return new ResponseEntity<>("Student with id " + id + " is deleted from DB", HttpStatus.OK);
    }

    @GetMapping("/getAll") //+
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>("All students in DB: " + studentServiceInter.getAll(), HttpStatus.OK);
    }

    @PostMapping("/add") //+
    public ResponseEntity<?> addStudent(@Valid @RequestBody StudentDto studentDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){ // -
            throw new IllegalArgumentException("Student Dto is not valid!");
        }
        studentServiceInter.addStudent(studentDto);
        return new ResponseEntity<>(studentDto.getName() + " " +
                    studentDto.getSurname() + " is added to DB", HttpStatus.CREATED);
    }

    @PutMapping("/update/{oldName}/{newName}") //+
    public ResponseEntity<?> update(@PathVariable String oldName, @PathVariable String newName){
        studentServiceInter.update(oldName, newName);
        return new ResponseEntity<>("Student with the name "+ oldName + " is changed to " + newName, HttpStatus.OK);
    }

    @PostMapping("assign/{studentName}/teachers/{teacherName}")//+
    public ResponseEntity<?> assignTeacherToStudent(
            @PathVariable String studentName, @PathVariable String teacherName)
    {
        StudentEntity studentEntity = studentServiceInter.getByStudentName(studentName);
        TeacherEntity teacherEntity = teacherServiceInter.getByName(teacherName);
        studentEntity.teachers.add(teacherEntity);
        studentServiceInter.save(studentEntity);
        return new ResponseEntity<>(studentEntity.getName() + " " + studentEntity.getSurname() +
                " is assigned to " + teacherEntity.getName() + " " + teacherEntity.getSurname(), HttpStatus.OK);
    }

    @GetMapping("/studentTeacher/{studentName}")//+
    public ResponseEntity<?> getTeacherByAssignedStudent(@PathVariable String studentName){
        StudentEntity studentEntity = studentServiceInter.getByStudentName(studentName);
        return new ResponseEntity<>(studentEntity.teachers,HttpStatus.OK);
    }

    @GetMapping("/studentSubject/{studentName}")
    public ResponseEntity<?> getSubjectByAssignedStudent(@PathVariable String studentName){
        StudentEntity studentEntity = studentServiceInter.getByStudentName(studentName);
        return new ResponseEntity<>(studentEntity.getCourses(), HttpStatus.OK);
    }
}



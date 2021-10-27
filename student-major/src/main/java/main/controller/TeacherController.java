package main.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import main.dto.TeacherDto;
import main.entity.TeacherEntity;
import main.serviceinter.TeacherServiceInter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Log
public class TeacherController {

    private final TeacherServiceInter teacherServiceInter;

    @GetMapping("/teacherName/{name}") //+
    public ResponseEntity<?> getName(@PathVariable String name){
        TeacherEntity teacherEntity = teacherServiceInter.getByName(name);
        return new ResponseEntity<TeacherEntity>(teacherEntity, HttpStatus.OK);
    }

    @DeleteMapping("/deleteTeacher/{id}") //+
    public ResponseEntity<?> delete(@PathVariable Integer id){
        teacherServiceInter.deleteById(id);
        return new ResponseEntity<>("Teacher with id: " + id + " is deleted from DB", HttpStatus.OK);
    }

    @GetMapping("/allTeachers") //+
    public ResponseEntity<?> getAll(){
        List<TeacherEntity> all = teacherServiceInter.getAll();
        return new ResponseEntity<>("All teachers in DB: " + all, HttpStatus.OK);
    }

    @PostMapping("/addTeacher") //+
    public ResponseEntity<?> add(@RequestBody TeacherDto teacherDto){
            teacherServiceInter.addTeacher(teacherDto);
            return new ResponseEntity<>(teacherDto.getName() + " " +
                     teacherDto.getSurname() + " is added to DB", HttpStatus.CREATED);
    }
}



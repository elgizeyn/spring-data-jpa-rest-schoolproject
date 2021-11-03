package main.controller;

import lombok.RequiredArgsConstructor;
import main.dto.CourseDto;
import main.entity.CourseEntity;
import main.entity.StudentEntity;
import main.entity.TeacherEntity;
import main.serviceinter.CourseServiceInter;
import main.serviceinter.StudentServiceInter;
import main.serviceinter.TeacherServiceInter;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CourseController {

    private final CourseServiceInter courseServiceInter;
    private final StudentServiceInter studentServiceInter;
    private final TeacherServiceInter teacherServiceInter;

    @PutMapping("/addCourse")//+
    public ResponseEntity<?> addCourse(@RequestBody CourseDto courseDto){
        courseServiceInter.addCourse(courseDto);
        return new ResponseEntity<>(courseDto.getName() + " course is added to DB", HttpStatus.OK);
    }

    @GetMapping("/allCourses")//+
    public ResponseEntity<?> getAll(){
        List<CourseEntity> courses = courseServiceInter.getAllCourses();
        return new ResponseEntity<>("All course in DB: " + courses, HttpStatus.OK);
    }

    @GetMapping("/courseByName/{name}") //+
    public ResponseEntity<?> getCourse(@PathVariable String name){
        CourseEntity course = courseServiceInter.getByCourseName(name);
        return new ResponseEntity<>(name + " course: " + course.getContent(), HttpStatus.OK);
    }

    @DeleteMapping("/deleteCourse/{id}") //+
    public ResponseEntity<?> deleteCourse(@PathVariable Integer id){
        courseServiceInter.deleteCourse(id);
        return new ResponseEntity<>("Course with " + id + " is deleted from DB", HttpStatus.OK);
    }

    @PutMapping("newName/{oldName}/{newName}")//+
    public ResponseEntity<?> updateName(@PathVariable String oldName, @PathVariable String newName){
        courseServiceInter.updateName(oldName, newName);
        return new ResponseEntity<>("The course with name " + oldName + " is updated to " + newName, HttpStatus.OK);
    }

    @PostMapping("enroll/{subjectName}/{studentName}")//+
    public ResponseEntity<?> enroll(
            @PathVariable String subjectName, @PathVariable String studentName)
    {
            CourseEntity courseEntity = courseServiceInter.getByCourseName(subjectName);
            StudentEntity studentEntity = studentServiceInter.getByStudentName(studentName);
            courseEntity.enrollStudent(studentEntity);
            studentEntity.addCourse(courseEntity);
            courseServiceInter.save(courseEntity);
            studentServiceInter.save(studentEntity);
            return new ResponseEntity<>(studentEntity.getName() + " " + studentEntity.getSurname()
                    + " is enrolled into " + courseEntity.getCourseName() + " course", HttpStatus.OK);
    }

    @PostMapping("assign/{subjectName}/{teacherName}")//+
    public ResponseEntity<?> assign(
            @PathVariable String subjectName, @PathVariable String teacherName)
    {
        CourseEntity courseEntity = courseServiceInter.getByCourseName(subjectName);
        TeacherEntity teacherEntity = teacherServiceInter.getByName(teacherName);
        courseEntity.assignTeacher(teacherEntity);
        courseServiceInter.save(courseEntity);
        return new ResponseEntity<>(teacherEntity.getName() + " " + teacherEntity.getSurname()
                + " is assigned to " + courseEntity.getCourseName() + " course", HttpStatus.OK);
    }

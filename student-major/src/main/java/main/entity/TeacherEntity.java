package main.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class TeacherEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String surname;


    @JsonIgnore
    @ManyToMany(mappedBy = "teachers")
    private List<StudentEntity> students = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "teacher")
    private List<CourseEntity> courses = new ArrayList<>();

    public List<CourseEntity> getCourses() {
        return courses;
    }

    public List<StudentEntity> getStudents() {
        return students;
    }

}

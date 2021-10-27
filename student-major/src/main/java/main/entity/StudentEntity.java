package main.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.util.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer student_id;

    private String name;
    private String surname;

    public List<CourseEntity> getCourses() {
        return courses;
    }

    @ManyToMany
    @JoinTable(
            name = "student_teacher",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id")
    )
    public List<TeacherEntity> teachers = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "enrolledStudents")
    public List<CourseEntity> courses = new ArrayList<>();
}

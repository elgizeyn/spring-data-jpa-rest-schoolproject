package main.entity;

import lombok.*;
import javax.persistence.*;
import java.util.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer course_id;

    private String courseName;
    private String content;

    @ManyToMany
    @JoinTable(name = "student_enrolled",
                joinColumns = @JoinColumn(name = "course_id"),
                inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    List<StudentEntity> enrolledStudents = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    private TeacherEntity teacher;

    public TeacherEntity getTeacher() {
        return teacher;
    }

    public void enrollStudent(StudentEntity studentEntity) {
        this.enrolledStudents.add(studentEntity);
    }

    public void assignTeacher(TeacherEntity teacher) {
        this.teacher = teacher;
    }

    public List<StudentEntity> getEnrolledStudents() {
        return enrolledStudents;
    }
}

package main.serviceinter;

import main.dto.CourseDto;
import main.entity.CourseEntity;
import java.util.List;

public interface CourseServiceInter {
    void addCourse(CourseDto courseDto);
    void deleteCourse(Integer id);
    void updateName(String oldName, String newName);
    List<CourseEntity> getAllCourses();
    CourseEntity getByCourseName(String name);
    void save(CourseEntity courseEntity);
}

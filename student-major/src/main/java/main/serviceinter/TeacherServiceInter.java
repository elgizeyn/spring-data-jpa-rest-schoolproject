package main.serviceinter;

import main.dto.TeacherDto;
import main.entity.TeacherEntity;
import java.util.List;

public interface TeacherServiceInter {
    TeacherEntity getById(Integer id);
    void deleteById(Integer id);
    List<TeacherEntity> getAll();
    void addTeacher(TeacherDto teacherDto);
    void save(TeacherEntity teacherEntity);
    TeacherEntity getByName(String name);
}

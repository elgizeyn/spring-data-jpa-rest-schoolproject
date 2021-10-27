package main.serviceinter;

import main.dto.StudentDto;
import main.entity.StudentEntity;
import java.util.List;

public interface StudentServiceInter {

    StudentEntity getById(Integer id);
    void update(String oldName, String newName);
    void deleteById(Integer id);
    List<StudentEntity> getAll();
    void addStudent(StudentDto studentDto);
    StudentEntity getByStudentName(String name);
    void save(StudentEntity studentEntity);
}

package main.implementation;

import main.dto.StudentDto;
import main.entity.StudentEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import main.repository.StudentRepository;
import main.serviceinter.StudentServiceInter;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log
public class StudentServiceImpl implements StudentServiceInter {

    private final StudentRepository studentRepository;

    @Override
    public StudentEntity getById(Integer id) {
        StudentEntity studentEntity = studentRepository.getById(id);
        return studentEntity;
    }

    @Override
    public void update(String oldName, String newName) {
        StudentEntity studentEntity = studentRepository.findByName(oldName);
        studentEntity.setName(newName);
        studentRepository.save(studentEntity);
    }


    @Override
    public void deleteById(Integer id) {
        studentRepository.deleteById(id);
        log.info("Student with " + id + " is deleted.");
    }

    @Override
    public List<StudentEntity> getAll() {
        List<StudentEntity> studentEntities = studentRepository.findAll();
        return studentEntities;
    }

    @Override
    public void addStudent(StudentDto studentDto) {
            StudentEntity studentEntity = mapToEntity(studentDto);
            studentRepository.save(studentEntity);
            log.info("Student entity with " + studentEntity.getName() + " is added.");
    }

    @Override
    public StudentEntity getByStudentName(String name) {
        StudentEntity studentEntity = studentRepository.findByName(name);
        return studentEntity;
    }

    @Override
    public void save(StudentEntity studentEntity) {
            studentRepository.save(studentEntity);
    }

    public StudentEntity mapToEntity(StudentDto studentDto){
        StudentEntity studentEntity = new StudentEntity();
        BeanUtils.copyProperties(studentDto, studentEntity);
        return studentEntity;
    }
}

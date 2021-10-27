package main.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import main.dto.TeacherDto;
import main.entity.TeacherEntity;
import main.repository.TeacherRepository;
import main.serviceinter.TeacherServiceInter;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log
public class TeacherServiceImpl implements TeacherServiceInter {

    private final TeacherRepository teacherRepository;


    @Override
    public TeacherEntity getById(Integer id) {
        TeacherEntity teacherEntity = teacherRepository.getById(id);
        return teacherEntity;
    }

    @Override
    public void deleteById(Integer id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public List<TeacherEntity> getAll() {
        List<TeacherEntity> teachers = teacherRepository.findAll();
        return teachers;
    }

    @Override
    public void addTeacher(TeacherDto teacherDto) {
        TeacherEntity teacherEntity = mapToEntity(teacherDto);
        teacherRepository.save(teacherEntity);
    }

    @Override
    public void save(TeacherEntity teacherEntity) {
        teacherRepository.save(teacherEntity);
    }

    @Override
    public TeacherEntity getByName(String name) {
        TeacherEntity teacherEntity = teacherRepository.findByName(name);
        return teacherEntity;
    }

    public TeacherEntity mapToEntity(TeacherDto teacherDto){
        TeacherEntity teacherEntity = new TeacherEntity();
        BeanUtils.copyProperties(teacherDto, teacherEntity);
        return teacherEntity;
    }
}

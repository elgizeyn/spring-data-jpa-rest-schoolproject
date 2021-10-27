package main.implementation;

import lombok.RequiredArgsConstructor;
import main.dto.CourseDto;
import main.entity.CourseEntity;
import main.repository.CourseRepository;
import main.serviceinter.CourseServiceInter;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseServiceInter {

    private final CourseRepository courseRepository;

    @Override
    public void addCourse(CourseDto courseDto) {
        CourseEntity courseEntity = mapToCourseEntity(courseDto);
        courseRepository.save(courseEntity);
    }

    @Override
    public void deleteCourse(Integer id) {
        courseRepository.deleteById(id);
    }


    @Override
    @Transactional
    public void updateName(String oldName, String newName) {
        CourseEntity courseEntity = courseRepository.findByCourseName(oldName);
        courseEntity.setCourseName(newName);
        courseRepository.save(courseEntity);
    }

    @Override
    public List<CourseEntity> getAllCourses() {
        List<CourseEntity> allCourses = courseRepository.findAll();
        return allCourses;
    }

    @Override
    public CourseEntity getByCourseName(String name) {
        return courseRepository.findByCourseName(name);
    }

//    @Override
//    public CourseEntity getByName(String name) {
//        CourseEntity courseEntity = courseRepository.findByCourseName(name)
//                .orElseThrow(()->new CourseNotFoundException("No Course found for given name: " + name));
//        return courseEntity;
//    }

    @Override
    public void save(CourseEntity courseEntity) {
        courseRepository.save(courseEntity);
    }

    public CourseEntity mapToCourseEntity(CourseDto courseDto){
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setCourseName(courseDto.getName());
        courseEntity.setContent(courseDto.getContent());
        return courseEntity;
    }
}

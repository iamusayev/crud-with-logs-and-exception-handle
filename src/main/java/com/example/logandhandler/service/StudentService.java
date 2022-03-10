package com.example.logandhandler.service;

import com.example.logandhandler.dao.entity.StudentEntity;
import com.example.logandhandler.dao.repository.StudentRepository;
import com.example.logandhandler.mapper.StudentMapper;
import com.example.logandhandler.model.constants.ExceptionConstant;
import com.example.logandhandler.model.dto.StudentDto;
import com.example.logandhandler.model.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.logandhandler.model.constants.ExceptionConstant.*;
import static com.example.logandhandler.model.constants.ExceptionConstant.UNEXPECTED_EXCEPTION_CODE;
import static com.example.logandhandler.model.enums.Status.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentService {

    private final StudentRepository repository;


    public void createStudent(StudentDto dto) {
        log.info("ActionLog.createStudent.start id : {}", dto.getId());

        repository.save(StudentMapper.mapDtoToEntity(dto));

        log.info("ActionLog.createStudent.end id : {}", dto.getId());
    }

    public StudentDto getStudent(Long id) {
        log.info("ActionLog.getStudent.start id: {}", id);

        StudentDto dto = StudentMapper.mapEntityToDto(fetchStudentIfExist(id));

        log.info("ActionLog.getStudent.end id: {}", id);
        return dto;
    }

    public List<StudentDto> getStudents() {
        log.info("ActionLog.getStudents.start");

        var dtos = repository
                .findAll()
                .stream()
                .map(StudentMapper::mapEntityToDto)
                .collect(Collectors.toList());

        log.info("ActionLog.getStudents.success");
        return dtos;
    }

    public void deleteStudent(Long id) {
        log.info("ActionLog.deleteStudent.start id: {}", id);

        StudentEntity entity = fetchStudentIfExist(id);
        entity.setStatus(REMOVED);
        repository.save(entity);

        log.info("ActionLog.deleteStudent.success id: {}", id);
    }


    public void updateStudent(Long id, StudentDto dto) {
        log.info("ActionLog.updatedStudent.start id: {}", id);

        StudentEntity entity = fetchStudentIfExist(id);
        entity.setName(dto.getName());
        entity.setLastname(dto.getLastname());
        entity.setAge(dto.getAge());
        repository.save(entity);

        log.info("ActionLog.updateStudent.success id: {}", id);
    }

    public void updateStudentName(Long id, String name) {
        log.info("ActionLog.updateStudentName.start id: {}", id);

        StudentEntity entity = fetchStudentIfExist(id);
        entity.setName(name);
        repository.save(entity);

        log.info("ActionLog.updateStudentName.success id: {}", id);
    }


    public void updateStudentLastname(Long id, String lastname) {
        log.info("ActionLog.updateStudentLastname.start id: {}", id);

        StudentEntity entity = fetchStudentIfExist(id);
        entity.setLastname(lastname);
        repository.save(entity);

        log.info("ActionLog.updateStudentLastname.success id: {}", id);
    }


    public void updateStudentAge(Long id, Integer age) {
        log.info("ActionLog.updateStudentAge.start id: {}", id);

        StudentEntity entity = fetchStudentIfExist(id);
        entity.setAge(age);
        repository.save(entity);

        log.info("ActionLog.updateStudentAge.success id: {}", id);
    }


    private StudentEntity fetchStudentIfExist(Long id) {
        return repository.findById(id).orElseThrow(() -> {
            throw new NotFoundException(String.format(STUDENT_NOT_FOUND_MESSAGE, id), STUDENT_NOT_FOUND_CODE);
        });
    }
}

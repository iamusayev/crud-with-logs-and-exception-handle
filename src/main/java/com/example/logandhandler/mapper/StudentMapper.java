package com.example.logandhandler.mapper;

import com.example.logandhandler.dao.entity.StudentEntity;
import com.example.logandhandler.model.enums.Status;
import com.example.logandhandler.model.dto.StudentDto;

public class StudentMapper {


    public static StudentEntity mapDtoToEntity(StudentDto dto) {
        return StudentEntity.builder()
                .name(dto.getName())
                .lastname(dto.getLastname())
                .age(dto.getAge())
                .status(Status.DRAFT)
                .build();
    }

    public static StudentDto mapEntityToDto(StudentEntity entity) {
        return StudentDto.builder()
                .name(entity.getName())
                .lastname(entity.getLastname())
                .age(entity.getAge())
                .build();
    }

}

package com.example.logandhandler.contoller;


import com.example.logandhandler.model.dto.StudentDto;
import com.example.logandhandler.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("v1/students")
@RequiredArgsConstructor
public class StudentController {


    private final StudentService service;

    @PostMapping
    @ResponseStatus(CREATED)
    public void createStudent(@RequestBody StudentDto dto) {
        service.createStudent(dto);
    }

    @GetMapping("/{id}")
    public StudentDto getStudent(@PathVariable Long id) {
        return service.getStudent(id);
    }


    @GetMapping
    public List<StudentDto> getStudents() {
        return service.getStudents();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteStudent(@PathVariable Long id) {
        service.deleteStudent(id);
    }

    @PutMapping("/{id}")
    public void updateStudent(@PathVariable Long id,
                              @RequestBody StudentDto dto) {
        service.updateStudent(id, dto);
    }

    @PatchMapping("/{id}/name")
    public void updateStudentName(@PathVariable Long id,
                                  @RequestParam(value = "value") String name) {
        service.updateStudentName(id, name);
    }

    @PatchMapping("/{id}/lastname")
    public void updateStudentLastname(@PathVariable Long id,
                                     @RequestParam(value = "value") String lastname ){
        service.updateStudentLastname(id,lastname);
    }

    @PatchMapping("/{id}/age")
    public void updateStudentAge(@PathVariable Long id,
                                 @RequestParam (value = "value") Integer age){
        service.updateStudentAge(id,age);
    }


}

package app.controller;

import app.dto.response.Response;
import app.dto.response.StudentChecking;
import app.model.StudentUni;
import app.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/check/{code}")
    public ResponseEntity<Response> checkStudentCode(@PathVariable("code") String roleNumber) {
        StudentChecking studentChecking = studentService.checkStudentRoleNumber(roleNumber.trim());
        return ResponseEntity.ok(Response.builder()
                        .httpStatus(HttpStatus.OK)
                        .object(studentChecking)
                        .build()
        );
    }


    @GetMapping("/list")
    public ResponseEntity<Response<List<StudentUni>>> getAllStudents() {
        List<StudentUni> listStudent = studentService.getAllStudents();

        Response<List<StudentUni>> response = Response.<List<StudentUni>>builder()
                .message("")
                .object(listStudent)
                .httpStatus(listStudent == null ? HttpStatus.NOT_FOUND : HttpStatus.OK)
                .build();

        return ResponseEntity.ok(response);
    }

}

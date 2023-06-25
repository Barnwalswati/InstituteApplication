package com.instituteApplication.controller;

import com.instituteApplication.dto.BatchDto;
import com.instituteApplication.model.Batch;
import com.instituteApplication.model.Student;
import com.instituteApplication.service.IStudentService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final IStudentService stuService;

    @Autowired
    public StudentController(IStudentService stuService) {
        this.stuService = stuService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> addStudent(@Valid @RequestBody Student student, BindingResult result) {
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            return new ResponseEntity(errors.get(0).getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(stuService.addStudent(student), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    public ResponseEntity<Student> findById(@PathVariable Long id) {
        return new ResponseEntity<>(stuService.findByStudentId(id), HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<Student>> getAllStudents () {
        return new ResponseEntity<>(stuService.findAllStudents(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable long id){
        return new ResponseEntity<>(stuService.updateStudent(student, id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteStudent(@PathVariable long id){
        return new ResponseEntity<>(stuService.deleteStudent(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/subscribedBatches", method = RequestMethod.POST)
    public ResponseEntity<String> subscribedBatch(@RequestParam long batchId, @RequestParam long studentId){
        return new ResponseEntity<>(stuService.subscribedBatch(batchId, studentId), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/subscribedBatches", method = RequestMethod.GET)
    public ResponseEntity<List<BatchDto>> getSubscribedBatches(@RequestParam long id) {
        Student student = stuService.findByStudentId(id);
        return new ResponseEntity<>(stuService.getSubscribedBatches(student), HttpStatus.OK);
    }

    @RequestMapping(value = "/{studentId}/subscribedBatches/{batchId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteSubscribedBatch(@PathVariable long studentId, @PathVariable long batchId) {
        return new ResponseEntity<>(stuService.deleteSubscribedBatch(studentId, batchId), HttpStatus.OK);
    }
}

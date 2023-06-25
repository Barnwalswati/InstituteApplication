package com.instituteApplication.controller;

import com.instituteApplication.model.Classes;
import com.instituteApplication.service.IClassService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/classes")
public class ClassController {

    @Autowired
    private IClassService classService;
    @GetMapping("/{classId}")
    public ResponseEntity<Classes> getClassById(@PathVariable long classId){
        return new ResponseEntity<>(classService.findById(classId), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<Classes>> getAllClass(){
        return new ResponseEntity<>(classService.findAllClasses(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('FACULTY')")
    @PutMapping("/{classId}")
    public ResponseEntity<String> updateClassById(@PathVariable long classId, @RequestBody Classes clazz){
        return new ResponseEntity<>(classService.updateClassById(classId, clazz), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('FACULTY')")
    @DeleteMapping("/{classId}")
    public ResponseEntity<String> deleteClassById(@PathVariable long classId){
        return new ResponseEntity<>(classService.deleteClassById(classId), HttpStatus.OK);
    }
}

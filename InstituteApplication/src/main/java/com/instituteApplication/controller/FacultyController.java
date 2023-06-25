package com.instituteApplication.controller;

import com.instituteApplication.model.Classes;
import com.instituteApplication.model.Faculty;
import com.instituteApplication.service.IFacultyService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/faculties")
public class FacultyController {

    private final IFacultyService facultyService;

    @Autowired
    public FacultyController(IFacultyService facultyService) {
        this.facultyService = facultyService;
    }

//    @PreAuthorize("hasRole('ADMIN')")
//    @RequestMapping(value = "", method = RequestMethod.POST)
//    public ResponseEntity<Faculty> addFaculty(@RequestBody Faculty faculty){
//        return new ResponseEntity<>(facultyService.addFaculty(faculty), HttpStatus.CREATED);
//    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('FACULTY')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Faculty> getFacultyById(@PathVariable long id){
        return new ResponseEntity<>(facultyService.getFaculty(id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('FACULTY')")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<Faculty>> getAllFactulty(){
        return new ResponseEntity<>(facultyService.getAllFaculty(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteFaculty(@PathVariable long id){
        return new ResponseEntity<>(facultyService.deleteFaculty(id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public ResponseEntity<Faculty> updateFaculty(@RequestBody Faculty faculty, @PathVariable long id){
        return new ResponseEntity<>(facultyService.updateFaculty(faculty, id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('FACULTY')")
    @RequestMapping(value = "/batches/{batchId}/classes", method = RequestMethod.POST)
    public ResponseEntity<Classes> addClassToBatch(@RequestBody Classes classes, @PathVariable long batchId){
        return  new ResponseEntity<>(facultyService.addClassToBatch(classes, batchId), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/batches/{batchId}/classes", method = RequestMethod.GET)
    public ResponseEntity<List<Classes>> getClassesOfBatch(@PathVariable long batchId){
        return new ResponseEntity<>(facultyService.getClassesOfBatch(batchId), HttpStatus.FOUND);
    }

}


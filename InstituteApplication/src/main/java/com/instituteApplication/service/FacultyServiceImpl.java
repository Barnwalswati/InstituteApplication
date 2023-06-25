package com.instituteApplication.service;

import com.instituteApplication.dao.BatchDao;
import com.instituteApplication.dao.ClassDao;
import com.instituteApplication.dao.FacultyDao;
import com.instituteApplication.model.Batch;
import com.instituteApplication.model.Classes;
import com.instituteApplication.model.Faculty;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class FacultyServiceImpl implements IFacultyService {

    private final FacultyDao facultyDao;
    private final BatchDao batchDao;
    private final ClassDao classDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public FacultyServiceImpl(FacultyDao facultyDao, BatchDao batchDao, ClassDao classDao,
                              PasswordEncoder passwordEncoder) {
        this.facultyDao = facultyDao;
        this.batchDao = batchDao;
        this.classDao = classDao;
        this.passwordEncoder = passwordEncoder;
    }

//    @Override
//    public Faculty addFaculty(Faculty faculty) {
//        faculty.setPassword(passwordEncoder.encode(faculty.getPassword()));
//        return facultyDao.save(faculty);
//    }

    @Override
    public Faculty getFaculty(long id) {
        return facultyDao.findById(id).orElseThrow(() -> new NoSuchElementException("faculty not found"));
    }

    @Override
    public List<Faculty> getAllFaculty() {
        return facultyDao.findAll();
    }

    @Override
    public String deleteFaculty(long id) {
        Faculty faculty = facultyDao.findById(id).orElseThrow(() -> new NoSuchElementException("faculty not found"));
        facultyDao.delete(faculty);
        return "Faculty deleted successfully";
    }

    @Override
    public Faculty updateFaculty(Faculty faculty, long id) {
        Faculty newFaculty = facultyDao.findById(id).orElseThrow(() -> new NoSuchElementException("Please insert a valid id"));
        newFaculty.setName(faculty.getName());
        //newFaculty.setBatches(faculty.getBatches());
        return facultyDao.save(newFaculty);
    }

    @Override
    public Classes addClassToBatch(Classes classes, long batchid) {
        Batch batch = batchDao.findById(batchid).orElseThrow(() -> new NoSuchElementException("Batch not found"));
        batch.getClasses().add(classes);
        batchDao.save(batch);
        return classes;
    }

    @Override
    public List<Classes> getClassesOfBatch(long batchId) {
        Batch batch = batchDao.findById(batchId).orElseThrow(() -> new NoSuchElementException("No batch found"));
        List<Classes> classesList = new ArrayList<>();
        classesList.addAll(batch.getClasses());
        return classesList;
    }
}

package com.instituteApplication.service;

import com.instituteApplication.dao.ClassDao;
import com.instituteApplication.model.Classes;
import java.util.List;
import java.util.NoSuchElementException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassServiceImpl implements IClassService{

    @Autowired
    private ClassDao classDao;

    @Override
    public Classes findById(long classId) {
        return classDao.findById(classId).orElseThrow(() -> new NoSuchElementException("No class found"));
    }

    @Override
    public List<Classes> findAllClasses() {
        return classDao.findAll();
    }

    @Override
    @Transactional
    public String updateClassById(long id, Classes clazz) {
        Classes c = classDao.findById(id).orElseThrow(() -> new NoSuchElementException("Class id not found"));
        c.setClassname(clazz.getClassname());
        c.setSchedule(clazz.getSchedule());
        classDao.save(c);
        return "Class details updated successfully";
    }

    @Override
    @Transactional
    public String deleteClassById(long id) {
        Classes c = classDao.findById(id).orElseThrow(() -> new NoSuchElementException("Class id not found"));
        classDao.delete(c);
        return "Class deleted successfully";
    }
}

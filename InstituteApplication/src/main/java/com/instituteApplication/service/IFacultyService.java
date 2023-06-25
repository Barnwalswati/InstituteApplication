package com.instituteApplication.service;

import com.instituteApplication.model.Classes;
import com.instituteApplication.model.Faculty;
import java.util.List;
import org.springframework.stereotype.Service;


public interface IFacultyService {

    //Faculty addFaculty(Faculty faculty);
    Faculty getFaculty(long id);
    List<Faculty> getAllFaculty();
    String deleteFaculty(long id);
    Faculty updateFaculty(Faculty faculty, long id);
    Classes addClassToBatch(Classes classes, long batchid);
    List<Classes> getClassesOfBatch(long batchId);
}

package com.instituteApplication.service;

import com.instituteApplication.model.Classes;
import java.util.List;
import org.springframework.stereotype.Service;

public interface IClassService {

    Classes findById(long classId);
    List<Classes> findAllClasses();
    String updateClassById(long id, Classes clazz);
    String deleteClassById(long id);
}

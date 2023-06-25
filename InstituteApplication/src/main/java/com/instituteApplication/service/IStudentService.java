package com.instituteApplication.service;

import com.instituteApplication.dto.BatchDto;
import com.instituteApplication.model.Student;
import java.util.List;

public interface IStudentService {

    Student addStudent(Student student);
    Student updateStudent(Student student, Long studentId);
    String deleteStudent(Long studentId);
    Student findByStudentId(Long studentId);
    List<Student> findAllStudents();
    String subscribedBatch(long batchId, long studentId);
    List<BatchDto> getSubscribedBatches(Student student);
    String deleteSubscribedBatch(long studentId, Long batchId);
}

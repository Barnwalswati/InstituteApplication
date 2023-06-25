package com.instituteApplication.service;

import com.instituteApplication.dao.BatchDao;
import com.instituteApplication.dao.StudentDao;
import com.instituteApplication.dto.BatchDto;
import com.instituteApplication.model.Batch;
import com.instituteApplication.model.Student;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements IStudentService{

    private StudentDao studentDao;
    private BatchDao batchDao;

    public StudentServiceImpl(StudentDao studentDao, BatchDao batchDao) {
        this.studentDao = studentDao;
        this.batchDao = batchDao;
    }

    @Override
    public Student addStudent(Student student) {
        return studentDao.save(student);
    }

    @Override
    public Student updateStudent(Student student, Long studentId) {
        Student stu = studentDao.findById(studentId).orElse(null);
        stu.setName(student.getName());
        stu.setSubscribedBatches(student.getSubscribedBatches());
        return studentDao.save(stu);
    }

    @Override
    public String deleteStudent(Long studentId) {
        Student student = studentDao.findById(studentId).orElse(null);
        if (student != null) {
            studentDao.delete(student);
            return "Student Deleted successfully";
        } else {
            return "Student Not Found";
        }
    }

    @Override
    public Student findByStudentId(Long studentId) {
        return studentDao.findById(studentId).orElseThrow(() -> new NoSuchElementException("Student not found"));
    }

    @Override
    public List<Student> findAllStudents() {
        return studentDao.findAll();
    }

    @Override
    @Transactional
    public String subscribedBatch(long batchId, long studdentId) {
        Batch batch = batchDao.findById(batchId).orElseThrow(() -> new NoSuchElementException("Batch not found"));
        Student student = studentDao.findById(studdentId).orElseThrow(() -> new NoSuchElementException("Student not found"));
        boolean isAlreadySubscribes = batch.getStudents().stream().anyMatch(s -> s.getId()==studdentId);
        if (isAlreadySubscribes){
            return "This Batch id: "+batchId+ " is already subscribed by student "+studdentId;
        }
        student.getSubscribedBatches().add(batch);
        studentDao.save(student);

        batch.getStudents().add(student);
        batch.setNoOfEnrolledStudents(batch.getNoOfEnrolledStudents()+1);
        batchDao.save(batch);
        return "Batch subscribed successfully";
    }

    @Override
    public List<BatchDto> getSubscribedBatches(Student student) {
        List<Batch> batches = student.getSubscribedBatches();
        return batches.stream().map(b ->
        {
            BatchDto batchDto = new BatchDto();
            batchDto.setBatchId(b.getBatchId());
            batchDto.setBatchName(b.getBatchName());
            batchDto.setStartdate(b.getStartdate());
            return batchDto;
        }).collect(Collectors.toList());
    }

    public String deleteSubscribedBatch(long studentId, Long batchId){
        Batch batch = batchDao.findById(batchId).orElseThrow(() -> new NoSuchElementException("Batch not found"));
        Student student = studentDao.findById(studentId).orElseThrow(() -> new NoSuchElementException("Student not found"));
        //check batchid is associated with this studentid
        Boolean isAssociatedBatch = student.getSubscribedBatches().stream().anyMatch(s -> s.getBatchId()==batchId);
        if(isAssociatedBatch){
            student.getSubscribedBatches().remove(batch);
            batch.getStudents().remove(student);
            batch.setNoOfEnrolledStudents(batch.getNoOfEnrolledStudents()-1);
            studentDao.save(student);
            batchDao.save(batch);
            return "The Batch with ID " + batchId + " has been removed from the student's subscribed batches.";
        }
        return "The Batch with ID " + batchId + " is not associated with the student's subscribed batches.";
    }
}

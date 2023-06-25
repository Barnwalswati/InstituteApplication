package com.instituteApplication.service;

import com.instituteApplication.dao.BatchDao;
import com.instituteApplication.dao.ClassDao;
import com.instituteApplication.dao.StudentDao;
import com.instituteApplication.model.Batch;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BatchServiceImpl implements IBatchService{

    private BatchDao batchDao;
    private ClassDao classDao;
    private IStudentService studentService;

    @Autowired
    public BatchServiceImpl(BatchDao batchDao, ClassDao classDao, IStudentService studentService) {
        this.batchDao = batchDao;
        this.classDao = classDao;
        this.studentService = studentService;
    }

    @Override
    public Batch getBatch(long batchId) {
        return batchDao.findById(batchId).orElseThrow(() -> new NoSuchElementException("No batch found"));
    }

    @Override
    public Batch addBatch(Batch batch) {
        return batchDao.save(batch);
    }

    @Override
    public List<Batch> getAllBatches() {
        return batchDao.findAll();
    }

    @Override
    public Batch updateBatch(Batch batch, long id) {
        Batch b = batchDao.findById(id).orElseThrow(() -> new NoSuchElementException("Please insert a valid batch Id"));
        b.setBatchName(batch.getBatchName());
        b.setStartdate(batch.getStartdate());
        //b.setClasses(batch.getClasses());
        return batchDao.save(b);
    }

    @Override
    public String deleteBatch(long batchId) {
        Batch batch = batchDao.findById(batchId).orElseThrow(() -> new NoSuchElementException("No batch found"));
        batch.getStudents()
                .forEach(s -> studentService.deleteSubscribedBatch(s.getId(), batchId));
        batchDao.delete(batch);
        return "Batch deleted successfully";
    }
}

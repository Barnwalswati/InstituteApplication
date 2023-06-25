package com.instituteApplication.service;

import com.instituteApplication.model.Batch;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface IBatchService {

    Batch getBatch(long batchId);
    Batch addBatch(Batch batch);
    List<Batch> getAllBatches();
    Batch updateBatch(Batch batch, long id);
    String deleteBatch(long batchId);

}

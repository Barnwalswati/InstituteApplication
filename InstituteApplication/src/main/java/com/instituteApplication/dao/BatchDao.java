package com.instituteApplication.dao;

import com.instituteApplication.model.Batch;
import com.instituteApplication.model.Classes;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BatchDao extends JpaRepository<Batch, Long> {

}

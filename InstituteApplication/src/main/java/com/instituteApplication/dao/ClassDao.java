package com.instituteApplication.dao;

import com.instituteApplication.model.Classes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassDao extends JpaRepository<Classes, Long> {

}

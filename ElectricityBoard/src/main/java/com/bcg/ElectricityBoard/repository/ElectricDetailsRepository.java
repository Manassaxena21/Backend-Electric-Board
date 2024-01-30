package com.bcg.ElectricityBoard.repository;

import com.bcg.ElectricityBoard.model.ElectricDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ElectricDetailsRepository extends JpaRepository<ElectricDetails,Long> {
    //List<ElectricDetails> findByApplicantId(long idNumber);
}

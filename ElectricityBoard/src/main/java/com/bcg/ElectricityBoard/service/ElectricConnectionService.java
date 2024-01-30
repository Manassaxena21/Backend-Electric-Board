package com.bcg.ElectricityBoard.service;

import com.bcg.ElectricityBoard.exception.ResourceNotFoundException;
import com.bcg.ElectricityBoard.model.ElectricDetails;
import com.bcg.ElectricityBoard.repository.ElectricDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElectricConnectionService {

    @Autowired
    private ElectricDetailsRepository electricDetailsRepository;

    public List<ElectricDetails> getAllConnections() {
        return electricDetailsRepository.findAll();
    }
    public ElectricDetails getConnectionById(int id)
    {
        return electricDetailsRepository.findById((long) id).
                orElseThrow(() -> new ResourceNotFoundException("Electric connection not found with ID: " + id));
    }
    public ElectricDetails updateConnection(ElectricDetails updatedDetails) {
        int id = updatedDetails.getId();
        ElectricDetails existingDetails = getConnectionById(id);

        // Validate and update fields
        if (updatedDetails.getLoadApplied() > 2000) {
            throw new IllegalArgumentException("LoadApplied cannot exceed 2000.");
        }

        // Don't allow changes to idNumber and applicantName
        updatedDetails.setIdNumber(existingDetails.getIdNumber());
        updatedDetails.setApplicantName(existingDetails.getApplicantName());

        // Other fields can be updated
        existingDetails.setGender(updatedDetails.getGender());
        existingDetails.setDistrict(updatedDetails.getDistrict());
        existingDetails.setState(updatedDetails.getState());
        existingDetails.setPinCode(updatedDetails.getPinCode());
        existingDetails.setOwnership(updatedDetails.getOwnership());
        existingDetails.setGovtIdType(updatedDetails.getGovtIdType());
        existingDetails.setCategory(updatedDetails.getCategory());
        existingDetails.setLoadApplied(updatedDetails.getLoadApplied());
        existingDetails.setDateOfApplication(updatedDetails.getDateOfApplication());
        existingDetails.setDateOfApproval(updatedDetails.getDateOfApproval());
        existingDetails.setModificationDate(updatedDetails.getModificationDate());
        existingDetails.setStatus(updatedDetails.getStatus());
        existingDetails.setReviewerId(updatedDetails.getReviewerId());
        existingDetails.setReviewerName(updatedDetails.getReviewerName());
        existingDetails.setReviewerComments(updatedDetails.getReviewerComments());

        // Save the updated entity
        return electricDetailsRepository.save(updatedDetails);
    }

}

package com.bcg.ElectricityBoard.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.time.LocalDate;

@Entity
public class ElectricDetails {
    public ElectricDetails() {
        // This constructor is required by Hibernate
    }
    //Entity class
    public ElectricDetails(int id, String applicantName, String gender, String district, String state,
                           int pinCode, String ownership, String govtIdType, long idNumber, String category,
                           Double loadApplied, LocalDate dateOfApplication, LocalDate dateOfApproval, LocalDate modificationDate,
                           String status, int reviewerId, String reviewerName, String reviewerComments) {
        this.id = id;
        this.applicantName = applicantName;
        this.gender = gender;
        this.district = district;
        this.state = state;
        this.pinCode = pinCode;
        this.ownership = ownership;
        this.govtIdType = govtIdType;
        this.idNumber = idNumber;
        this.category = category;
        this.loadApplied = loadApplied;
        this.dateOfApplication = dateOfApplication;
        this.dateOfApproval = dateOfApproval;
        this.modificationDate = modificationDate;
        this.status = status;
        this.reviewerId = reviewerId;
        this.reviewerName = reviewerName;
        this.reviewerComments = reviewerComments;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "Applicant_Name",updatable = false)
    String applicantName;
    @Column(name ="Gender")
    String gender;
    @Column(name = "District")
    String district;
    @Column(name = "State")
    String state;
    @Column(name = "Pincode")
    int pinCode;
    @Column(name = "Ownership")
    String ownership;
    @Column(name = "GovtID_Type")
    String govtIdType;
    @Column(name = "ID_Number",updatable = false) //Updating not allowed
    long idNumber;
    @Column(name = "Category")
    String category;
    @Min(0)
    @Max(2000) // Setting value for Min and Max
    @Column(name="Load_Applied")
    double loadApplied;
    @Column(name = "Date_of_Application")
    LocalDate dateOfApplication;
    @Column(name = "Date_of_Approval")
    LocalDate dateOfApproval;
    @Column(name = "Modified_Date")
    LocalDate modificationDate;
    @Column(name = "Status")
    String status;
    @Column(name = "Reviewer_ID")
    int reviewerId;
    @Column(name = "Reviewer_Name")
    String reviewerName;
    @Column(name = "Reviewer_Comments")
    String reviewerComments;

    //Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getPinCode() {
        return pinCode;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }

    public String getOwnership() {
        return ownership;
    }

    public void setOwnership(String ownership) {
        this.ownership = ownership;
    }

    public String getGovtIdType() {
        return govtIdType;
    }

    public void setGovtIdType(String govtIdType) {
        this.govtIdType = govtIdType;
    }

    public long getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(long idNumber) {
        this.idNumber = idNumber;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getLoadApplied() {
        return loadApplied;
    }

    public void setLoadApplied(Double loadApplied) {
        this.loadApplied = loadApplied;
    }

    public LocalDate getDateOfApplication() {
        return dateOfApplication;
    }

    public void setDateOfApplication(LocalDate dateOfApplication) {
        this.dateOfApplication = dateOfApplication;
    }

    public LocalDate getDateOfApproval() {
        return dateOfApproval;
    }

    public void setDateOfApproval(LocalDate dateOfApproval) {
        this.dateOfApproval = dateOfApproval;
    }

    public LocalDate getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(LocalDate modificationDate) {
        this.modificationDate = modificationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(int reviewerId) {
        this.reviewerId = reviewerId;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    public String getReviewerComments() {
        return reviewerComments;
    }

    public void setReviewerComments(String reviewerComments) {
        this.reviewerComments = reviewerComments;

    }
    public Integer getNullableField(Integer value) {
        return (value != null) ? value : 0; // You can modify this logic based on your requirements
    }
}

package com.roe.almaserver.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class PortfolioDto {
    private Long id;
    private String name;
    private  String offeringName;
    private  String customerName;
    private  String amount;
    private  Timestamp creationDate;
    private  String priority;
    private  String status;
    private  String location;

    private  String summary;

    @JsonIgnore
    private SyndicatorDto syndicatorDto;

    private List<UploadedFileDto> uploadedFiles = new ArrayList<>();

    public PortfolioDto(){}
    public Long getId() {
        return id;
    }

    public PortfolioDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public PortfolioDto setName(String name) {
        this.name = name;
        return this;
    }

    public SyndicatorDto getSyndicatorDto() {
        return syndicatorDto;
    }

    public void setSyndicatorDto(SyndicatorDto syndicatorDto) {
        this.syndicatorDto = syndicatorDto;
    }

    public String getOfferingName() {
        return offeringName;
    }

    public void setOfferingName(String offeringName) {
        this.offeringName = offeringName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<UploadedFileDto> getUploadedFiles() {
        return uploadedFiles;
    }

    public void setUploadedFiles(List<UploadedFileDto> uploadedFiles) {
        this.uploadedFiles = uploadedFiles;
    }

    public String getLocation() { return location; }

    public void setLocation(String location) { this.location = location; }

    public String getSummary() { return summary; }

    public void setSummary(String summary) { this.summary = summary; }
}

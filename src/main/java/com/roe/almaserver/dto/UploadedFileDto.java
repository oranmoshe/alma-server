package com.roe.almaserver.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UploadedFileDto {

    private Long id;
    private String name;
    private String path;
    private String uploadType;
    @JsonIgnore
    private PortfolioDto portfolio;

    public UploadedFileDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public PortfolioDto getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(PortfolioDto portfolio) {
        this.portfolio = portfolio;
    }

    public String getUploadType() {
        return uploadType;
    }

    public void setUploadType(String uploadType) {
        this.uploadType = uploadType;
    }
}

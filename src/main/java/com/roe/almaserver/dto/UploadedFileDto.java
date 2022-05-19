package com.roe.almaserver.dto;

import javax.persistence.Column;

public class UploadedFileDto {
    private Long id;
    private String name;
    private String extension;
    private String fullName;
    private String path;

    public Long getId() {
        return id;
    }

    public UploadedFileDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public UploadedFileDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getExtension() {
        return extension;
    }

    public UploadedFileDto setExtension(String extension) {
        this.extension = extension;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public UploadedFileDto setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getPath() {
        return path;
    }

    public UploadedFileDto setPath(String path) {
        this.path = path;
        return this;
    }
}

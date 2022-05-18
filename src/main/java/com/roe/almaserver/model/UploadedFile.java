package com.roe.almaserver.model;

import javax.persistence.*;

@MappedSuperclass
public class UploadedFile {
    @Id
    @SequenceGenerator(name="uploaded_files_seq",sequenceName="uploaded_files_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="uploaded_files_seq")
    @Column(name = "att_id")
    private Long id;
    @Column(name = "att_name")
    private String name;
    @Column(name = "att_extension")
    private String extension;
    @Column(name = "att_full_name")
    private String fullName;
    @Column(name = "att_path")
    private String path;

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

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

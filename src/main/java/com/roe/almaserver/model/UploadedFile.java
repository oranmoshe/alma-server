package com.roe.almaserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.File;
import java.io.IOException;

@Entity(name = "UploadedFile")
@Table(name = "uploaded_files")
public class UploadedFile {
    @Id
    @SequenceGenerator(name="uploaded_files_seq",sequenceName="uploaded_files_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="uploaded_files_seq")
    @Column(name = "upf_id")
    private Long id;
    @Column(name = "upf_name")
    private String name;
    @Column(name = "upf_path")
    private String path;

    @Column(name = "upf_type")
    private String type;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Portfolio.class, optional=false)
    @JsonIgnore
    @JoinColumn(name = "set_sit_id")
    private Portfolio portfolio;

    public UploadedFile(){}

    public UploadedFile(MultipartFile file, String path, Portfolio portfolio) throws IOException {
        this.name = file.getName();
        this.type = file.getContentType();
        this.path = path;
        this.portfolio = portfolio;
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

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    public String getPath() { return path; }

    public void setPath(String path) { this.path = path; }

}

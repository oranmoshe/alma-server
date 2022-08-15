package com.roe.almaserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity(name = "Portfolio")
@Table(name = "portfolios")
public class Portfolio {
    @Id
    @SequenceGenerator(name="portfolios_seq",sequenceName="portfolios_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="portfolios_seq")
    @Column(name = "prt_id")
    private Long id;

    @ManyToOne(targetEntity = Syndicator.class)
    @JsonIgnore
    @JoinColumn(name = "prt_syn_id")
    private Syndicator syndicator;

    @Column(name = "prt_name")
    private  String name;

    @Column(name = "prt_offering_name")
    private  String offeringName;

    @Column(name = "prt_customer_name")
    private  String customerName;

    @Column(name = "prt_amount")
    private  String amount;

    @Column(name = "prt_creation_date")
    private Timestamp creationDate;

    @Column(name = "prt_priority")
    private  String priority;

    @Column(name = "prt_status")
    private  String status;

    @Column(name = "prt_location")
    private  String location;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "portfolio", targetEntity = UploadedFile.class, orphanRemoval = true)
    private Set<UploadedFile> uploadedFiles = new HashSet<>();

    @Column(name = "prt_summary")
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private  String summary;

    public Portfolio(){}

    public Portfolio(Syndicator syndicator) {
        setSyndicator(syndicator);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Syndicator getSyndicator() {
        return syndicator;
    }

    public void setSyndicator(Syndicator syndicator) {
        this.syndicator = syndicator;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void addAssetAttachments(UploadedFile uploadedFile) {
        this.uploadedFiles.add(uploadedFile);
    }

    public void removeAssetAttachments(Long uploadedFileId) {
        this.uploadedFiles.removeIf(u->u.getId().equals(uploadedFileId));
    }

    public Set<UploadedFile> getUploadedFiles() { return uploadedFiles; }

    public void setUploadedFiles(Set<UploadedFile> uploadedFiles) { this.uploadedFiles = uploadedFiles; }

    public String getLocation() { return location; }

    public void setLocation(String location) { this.location = location; }

    public String getSummary() { return summary; }

    public void setSummary(String summary) { this.summary = summary; }
}

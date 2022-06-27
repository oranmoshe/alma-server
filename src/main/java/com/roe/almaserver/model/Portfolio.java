package com.roe.almaserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.roe.almaserver.dto.PortfolioDto;

import javax.persistence.*;
import java.sql.Timestamp;

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

    private Portfolio(){}

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
}

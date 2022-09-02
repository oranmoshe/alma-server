package com.roe.almaserver.exceptions.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "Investor")
@Table(name = "investors")
public class Investor {
    @Id
    @SequenceGenerator(name="investors_seq",sequenceName="investors_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="investors_seq")
    @Column(name = "inv_id")
    private Long id;
    @Column(name = "inv_fname")
    private String fname;
    @Column(name = "inv_lname")
    private String lname;
    @Column(name = "inv_nickname")
    private String nickname;
    @Column(name = "inv_street_address1")
    private String streetAddress1;
    @Column(name = "inv_street_address2")
    private String streetAddress2;
    @Column(name = "inv_city")
    private String city;
    @Column(name = "inv_state")
    private String state;
    @Column(name = "inv_zip_code")
    private String zipCode;
    @Column(name = "inv_tax_id")
    private String taxId;
    @Column(name = "inv_phone")
    private String phone;
    @Column(name = "inv_email")
    private String email;
    @Column(name = "inv_password")
    private String password;
    @Column(name = "inv_accredited")
    private Boolean accredited;
    @Column(name = "inv_investment_capacity")
    private String investmentCapacity;
    @Column(name = "inv_date_of_birth")
    private Timestamp dateOfBirth;

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getStreetAddress1() {
        return streetAddress1;
    }

    public void setStreetAddress1(String streetAddress1) {
        this.streetAddress1 = streetAddress1;
    }

    public String getStreetAddress2() {
        return streetAddress2;
    }

    public void setStreetAddress2(String streetAddress2) {
        this.streetAddress2 = streetAddress2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAccredited() {
        return accredited;
    }

    public void setAccredited(Boolean accredited) {
        this.accredited = accredited;
    }

    public String getInvestmentCapacity() {
        return investmentCapacity;
    }

    public void setInvestmentCapacity(String investmentCapacity) {
        this.investmentCapacity = investmentCapacity;
    }

    public Timestamp getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Timestamp dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

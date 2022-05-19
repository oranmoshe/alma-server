package com.roe.almaserver.dto;

import java.sql.Timestamp;

public class InvestorDto {
    private Long id;
    private String fname;
    private String lname;
    private String nickname;
    private String streetAddress1;
    private String streetAddress2;
    private String city;
    private String state;
    private String zipCode;
    private String taxId;
    private String phone;
    private String email;
    private String password;
    private Boolean accredited;
    private String investmentCapacity;
    private Timestamp dateOfBirth;

    public Long getId() {
        return id;
    }

    public InvestorDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFname() {
        return fname;
    }

    public InvestorDto setFname(String fname) {
        this.fname = fname;
        return this;
    }

    public String getLname() {
        return lname;
    }

    public InvestorDto setLname(String lname) {
        this.lname = lname;
        return this;
    }

    public String getNickname() {
        return nickname;
    }

    public InvestorDto setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public String getStreetAddress1() {
        return streetAddress1;
    }

    public InvestorDto setStreetAddress1(String streetAddress1) {
        this.streetAddress1 = streetAddress1;
        return this;
    }

    public String getStreetAddress2() {
        return streetAddress2;
    }

    public InvestorDto setStreetAddress2(String streetAddress2) {
        this.streetAddress2 = streetAddress2;
        return this;
    }

    public String getCity() {
        return city;
    }

    public InvestorDto setCity(String city) {
        this.city = city;
        return this;
    }

    public String getState() {
        return state;
    }

    public InvestorDto setState(String state) {
        this.state = state;
        return this;
    }

    public String getZipCode() {
        return zipCode;
    }

    public InvestorDto setZipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public String getTaxId() {
        return taxId;
    }

    public InvestorDto setTaxId(String taxId) {
        this.taxId = taxId;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public InvestorDto setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public InvestorDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public InvestorDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public Boolean getAccredited() {
        return accredited;
    }

    public InvestorDto setAccredited(Boolean accredited) {
        this.accredited = accredited;
        return this;
    }

    public String getInvestmentCapacity() {
        return investmentCapacity;
    }

    public InvestorDto setInvestmentCapacity(String investmentCapacity) {
        this.investmentCapacity = investmentCapacity;
        return this;
    }

    public Timestamp getDateOfBirth() {
        return dateOfBirth;
    }

    public InvestorDto setDateOfBirth(Timestamp dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }
}

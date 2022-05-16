package com.roe.almaserver.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PortfolioDto {
    private Long id;
    @JsonIgnore
    private Long syndicatorId;
    private String name;
    public PortfolioDto(){}
    public Long getId() {
        return id;
    }

    public PortfolioDto setId(Long id) {
        this.id = id;
        return this;
    }

    @JsonIgnore
    public Long getSyndicatorId() {
        return syndicatorId;
    }
    @JsonProperty
    public PortfolioDto setSyndicatorId(Long syndicatorId) {
        this.syndicatorId = syndicatorId;
        return this;
    }

    public String getName() {
        return name;
    }

    public PortfolioDto setName(String name) {
        this.name = name;
        return this;
    }
}

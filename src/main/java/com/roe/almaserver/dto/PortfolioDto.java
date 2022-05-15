package com.roe.almaserver.dto;

import java.util.Set;

public class PortfolioDto {
    private Long id;
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

    public Long getSyndicatorId() {
        return syndicatorId;
    }

    public PortfolioDto setSyndicatorId(Long syndicatorId) {
        this.syndicatorId = syndicatorId;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

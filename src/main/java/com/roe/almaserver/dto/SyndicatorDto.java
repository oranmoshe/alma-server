package com.roe.almaserver.dto;

import java.util.Set;

public class SyndicatorDto {
    private Long id;
    private Set<PortfolioDto> portfolioDtoSet;
    private String name;

    public SyndicatorDto() {
    }

    public SyndicatorDto(Long id, Set<PortfolioDto> portfolioDtoSet) {
        this.id = id;
        this.portfolioDtoSet = portfolioDtoSet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SyndicatorDto setPortfolioDtoSet(Set<PortfolioDto> portfolioDtoSet) {
        this.portfolioDtoSet = portfolioDtoSet;
        return this;
    }

    public Set<PortfolioDto> getPortfolioDtoSet() {
        return portfolioDtoSet;
    }

    public SyndicatorDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return name;
    }
}

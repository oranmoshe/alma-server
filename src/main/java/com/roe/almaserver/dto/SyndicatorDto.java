package com.roe.almaserver.dto;

import java.util.Set;

public class SyndicatorDto {
    private Long id;
    private Set<PortfolioDto> portfolios;
    private String name;

    public SyndicatorDto() {
    }

    public SyndicatorDto(Long id, Set<PortfolioDto> portfolioDtoSet) {
        this.id = id;
        this.portfolios = portfolioDtoSet;
    }

    public Long getId() {
        return id;
    }

    public SyndicatorDto setId(Long id) {
        this.id = id;
        return this;
    }

    public SyndicatorDto setPortfolios(Set<PortfolioDto> portfolios) {
        this.portfolios = portfolios;
        return this;
    }

    public Set<PortfolioDto> getPortfolios() {
        return portfolios;
    }

    public SyndicatorDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return name;
    }
}

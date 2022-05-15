package com.roe.almaserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.roe.almaserver.dto.PortfolioDto;

import javax.persistence.*;

@Entity(name = "Portfolio")
@Table(name = "portfolios")
public class Portfolio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prt_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Syndicator.class)
    @JsonIgnore
    @JoinColumn(name = "prt_syn_id")
    private Syndicator syndicator;

    @Column(name = "prt_name")
    private  String name;

    public Portfolio(){}

    public Portfolio(Syndicator syndicator, PortfolioDto portfolioDto) {
        this.syndicator = syndicator;
    }

    public Portfolio bind(PortfolioDto portfolioDto) {
        setName(portfolioDto.getName());
        return this;
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
}

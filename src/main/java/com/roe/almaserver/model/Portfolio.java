package com.roe.almaserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.roe.almaserver.dto.PortfolioDto;

import javax.persistence.*;

@Entity(name = "Portfolio")
@Table(name = "portfolios")
public class Portfolio {
    @Id
    @SequenceGenerator(name="portfolios_seq",sequenceName="portfolios_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="portfolios_seq")
    @Column(name = "prt_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Syndicator.class)
    @JsonIgnore
    @JoinColumn(name = "prt_syn_id")
    private Syndicator syndicator;

    @Column(name = "prt_name")
    private  String name;

    public Portfolio(){}

    public Portfolio(Syndicator syndicator) {
        setSyndicator(syndicator);
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

package com.roe.almaserver.model;

import com.roe.almaserver.dto.SyndicatorDto;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity(name = "Syndicator")
@Table(name = "syndicators")
public class Syndicator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "syr_id")
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "syndicator", targetEntity = Portfolio.class)
    private Set<Portfolio> portfolios = new HashSet<>();

    @Column(name = "syr_name")
    private String name;

    @Column(name = "syr_activated")
    private boolean activated;

    public Syndicator() {}

    public Syndicator bind(SyndicatorDto syndicatorDto){
        setName(syndicatorDto.getName());
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Set<Portfolio> getPortfolios() {
        return portfolios;
    }

    public void setPortfolios(Set<Portfolio> portfolios) {
        this.portfolios = portfolios;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }
}

package com.roe.almaserver.exceptions.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Syndicator")
@Table(name = "syndicators")
public class Syndicator {
    @Id
    @SequenceGenerator(name = "syndicators_seq", sequenceName = "syndicators_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "syndicators_seq")
    @Column(name = "syr_id")
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "syndicator",
            targetEntity = Portfolio.class, orphanRemoval = true)
    private Set<Portfolio> portfolios = new HashSet<>();

    @Column(name = "syr_name")
    private String name;

    @Column(name = "syr_activated")
    private boolean activated;

    public Syndicator() {
        activated = true;
    }

//    public Syndicator bind(SyndicatorDto syndicatorDto) {
//        setName(syndicatorDto.getName());
//        bindPortfolios(syndicatorDto.getPortfolios());
//        return this;
//    }
//
//    public void bindPortfolios(Set<PortfolioDto> portfolioDtoSet) {
//        // portfolios
//        Set<PortfolioDto> newPortfolios = portfolioDtoSet.stream().filter(p -> p.getId() == null).collect(Collectors.toSet());
//        Map<Long, PortfolioDto> existingPortfoliosDtoMap = portfolioDtoSet.stream().filter(p -> p.getId() != null).collect(Collectors.toMap(PortfolioDto::getId, item -> item, (a, b) -> a));
//        // remove deleted
//        getPortfolios().removeIf(p -> !existingPortfoliosDtoMap.containsKey(p.getId()));
//        // update existing portfolios
//        getPortfolios().stream().filter(p -> existingPortfoliosDtoMap.containsKey(p.getId()))
//                .collect(Collectors.toSet())
//                .forEach(p -> p.bind(existingPortfoliosDtoMap.get(p.getId())));
//        // create new portfolios
//        newPortfolios.forEach(p -> getPortfolios().add(new Portfolio(this).bind(p)));
//    }

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

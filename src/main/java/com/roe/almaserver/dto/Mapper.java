package com.roe.almaserver.dto;

import com.roe.almaserver.model.Portfolio;
import com.roe.almaserver.model.Syndicator;

import java.util.stream.Collectors;

public class Mapper {
    public static SyndicatorDto toSyndicatorDto(Syndicator syndicator){
        return new SyndicatorDto()
                .setPortfolioDtoSet(syndicator.getPortfolios().stream().map(portfolio -> toPortfolioDto(portfolio)).collect(Collectors.toSet()))
                .setName(syndicator.getName());
    }

    public static PortfolioDto toPortfolioDto(Portfolio portfolio){
        return new PortfolioDto()
                .setId(portfolio.getId())
                .setSyndicatorId(portfolio.getSyndicator().getId());
    }
}

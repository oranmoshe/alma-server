package com.roe.almaserver.dto;

import com.roe.almaserver.model.Portfolio;
import com.roe.almaserver.model.Syndicator;
import org.modelmapper.ModelMapper;

public class Converter {

    final static ModelMapper modelMapper = new ModelMapper();

    public static SyndicatorDto convertToDto(Syndicator syndicator) {
        SyndicatorDto syndicatorDto = modelMapper.map(syndicator, SyndicatorDto.class);
        syndicatorDto.getPortfolios().forEach(p -> p.setSyndicatorDto(syndicatorDto));
        return syndicatorDto;
    }

    public static Syndicator convertToEntity(SyndicatorDto syndicatorDto) {
        Syndicator syndicator = modelMapper.map(syndicatorDto, Syndicator.class);
        syndicator.getPortfolios().forEach(p->p.setSyndicator(syndicator));
        return syndicator;
    }

    public static PortfolioDto convertToDto(Portfolio portfolio) {
        PortfolioDto portfolioDto = modelMapper.map(portfolio, PortfolioDto.class);
        portfolioDto.setSyndicatorDto(convertToDto(portfolio.getSyndicator()));
        portfolioDto.setAmount(portfolio.getAmount());
        portfolioDto.setCreationDate(portfolio.getCreationDate());
        portfolioDto.setCustomerName(portfolio.getCustomerName());
        portfolioDto.setPriority(portfolio.getPriority());
        portfolioDto.setStatus(portfolio.getStatus());
        portfolioDto.setOfferingName(portfolio.getOfferingName());
        return portfolioDto;
    }

    public static Portfolio convertToEntity(PortfolioDto portfolioDto) {
        return modelMapper.map(portfolioDto, Portfolio.class);
    }
}
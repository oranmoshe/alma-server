package com.roe.almaserver.controller;

import com.roe.almaserver.dto.Converter;
import com.roe.almaserver.dto.PortfolioDto;
import com.roe.almaserver.dto.SyndicatorDto;
import com.roe.almaserver.model.Portfolio;
import com.roe.almaserver.model.Syndicator;
import com.roe.almaserver.model.general.PaginatedResponse;
import com.roe.almaserver.services.SyndicatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/syndicators")
public class SyndicatorController {

    private final SyndicatorService syndicatorService;

    @Autowired
    public SyndicatorController(SyndicatorService syndicatorService) {
        this.syndicatorService = syndicatorService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public SyndicatorDto getSyndicator(Long id) {
        Syndicator syndicator = syndicatorService.getSyndicator(id);
        return Converter.convertToDto(syndicator);
    }

    @GetMapping(path = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public PaginatedResponse<SyndicatorDto> getSyndicatorList(Pageable pageable) {
        Page<Syndicator> syndicatorList = syndicatorService.getSyndicatorListPage(pageable);
        Page<SyndicatorDto> syndicatorDtoPage = syndicatorList.map(s -> Converter.convertToDto(s));
        return new PaginatedResponse<>(syndicatorDtoPage.getContent(), (int) syndicatorDtoPage.getTotalElements());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public SyndicatorDto addSyndicator(@RequestBody SyndicatorDto syndicatorDto) {
        Syndicator syndicator = Converter.convertToEntity(syndicatorDto);
        syndicator.getPortfolios().forEach(p -> p.setSyndicator(syndicator));
        return Converter.convertToDto(syndicatorService.createSyndicator(syndicator));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public SyndicatorDto updateSyndicatorDetails(@RequestBody SyndicatorDto syndicatorDto) {
        Syndicator syndicator = Converter.convertToEntity(syndicatorDto);
        return Converter.convertToDto(syndicatorService.updateSyndicatorDetails(syndicator));
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteSyndicator(@PathVariable Long id) {
        syndicatorService.removeSyndicator(id);
    }

    //
    // PORTFOLIOS
    //

    @GetMapping(path = "/{syndicatorid}/portfolio/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public PaginatedResponse<PortfolioDto> getPortfolioList(Pageable pageable, @PathVariable Long syndicatorid) {
        Page<Portfolio> portfolioList = syndicatorService.getPortfolioListPage(pageable, syndicatorid);
        Page<PortfolioDto> portfolioListDto = new PageImpl<>(portfolioList.getContent().stream().map(p -> Converter.convertToDto(p)).collect(Collectors.toList()));
        return new PaginatedResponse<>(portfolioListDto.getContent(), (int) portfolioListDto.getTotalElements());
    }

    @GetMapping(path = "/{syndicatorid}/portfolio/{portfolioid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PortfolioDto getPortfolio(@PathVariable Long syndicatorid, @PathVariable Long portfolioid) {
        return Converter.convertToDto(syndicatorService.getPortfolio(syndicatorid, portfolioid));
    }

    @PostMapping(path = "/{syndicatorid}/portfolio", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public PortfolioDto addPortfolio(@RequestBody PortfolioDto portfolioDto) {
        syndicatorService.addPortfolio(Converter.convertToEntity(portfolioDto));
        return portfolioDto;
    }

    @PutMapping(path = "/{syndicatorid}/portfolio", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PortfolioDto updatePortfolioDetails(@RequestBody PortfolioDto portfolioDto) {
        Portfolio portfolio = syndicatorService.updatePortfolioDetails(Converter.convertToEntity(portfolioDto));
        return Converter.convertToDto(portfolio);
    }

    @DeleteMapping(path = "/{syndicatorid}/portfolio/{portfolioid}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void removePortfolioFromSyndicator(@PathVariable Long syndicatorid, @PathVariable Long portfolioid) {
        syndicatorService.removePortfolio(syndicatorid, portfolioid);
    }
}

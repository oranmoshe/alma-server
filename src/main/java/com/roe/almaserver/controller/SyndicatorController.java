package com.roe.almaserver.controller;

import com.roe.almaserver.dto.PortfolioDto;
import com.roe.almaserver.dto.SyndicatorDto;
import com.roe.almaserver.model.general.PaginatedResponse;
import com.roe.almaserver.services.SyndicatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/syndicators")
public class SyndicatorController {

    private final SyndicatorService syndicatorService;

    @Autowired
    public SyndicatorController(SyndicatorService syndicatorService){
        this.syndicatorService = syndicatorService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public SyndicatorDto getSyndicator(Long id){
        return syndicatorService.getSyndicator(id);
    }

    @GetMapping(path = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public PaginatedResponse<SyndicatorDto> getSyndicatorList(Pageable pageable) {
        return syndicatorService.getSyndicatorListPage(pageable);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public SyndicatorDto addSyndicator(@RequestBody SyndicatorDto syndicatorDto) {
        return syndicatorService.createSyndicator(syndicatorDto);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public SyndicatorDto updateSyndicator(@RequestBody SyndicatorDto syndicatorDto) {
        return syndicatorService.updateSyndicator(syndicatorDto);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteSyndicator(@PathVariable Long id) {
        syndicatorService.removeSyndicator(id);
    }

    @GetMapping(path = "/{syndicatorid}/portfolio/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public PaginatedResponse<PortfolioDto> getPortfolioList(Pageable pageable, @PathVariable Long syndicatorid) {
        return syndicatorService.getPortfolioListPage(pageable, syndicatorid);
    }

    @GetMapping(path = "/{syndicatorid}/portfolio/{portfolioid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PortfolioDto getPortfolio(@PathVariable Long syndicatorid, @PathVariable Long portfolioid){
        return syndicatorService.getPortfolio(syndicatorid,portfolioid);
    }

    @PostMapping(path = "/{syndicatorid}/portfolio", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public PortfolioDto addPortfolio(@RequestBody PortfolioDto portfolioDto) {
        syndicatorService.addPortfolio(portfolioDto);
        return portfolioDto;
    }

    @PutMapping(path = "/{syndicatorid}/portfolio", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PortfolioDto updatePortfolio(@RequestBody PortfolioDto portfolioDto) {
        syndicatorService.updatePortfolio(portfolioDto);
        return portfolioDto;
    }

    @DeleteMapping(path = "/{syndicatorid}/portfolio/{portfolioid}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deletePortfolio(@PathVariable Long syndicatorid, @PathVariable Long portfolioid) {
        syndicatorService.removePortfolio(syndicatorid, portfolioid);
    }
}

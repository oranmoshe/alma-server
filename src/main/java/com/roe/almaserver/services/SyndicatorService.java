package com.roe.almaserver.services;

import com.roe.almaserver.dto.Mapper;
import com.roe.almaserver.dto.PortfolioDto;
import com.roe.almaserver.dto.SyndicatorDto;
import com.roe.almaserver.exceptions.EntityNotFoundException;
import com.roe.almaserver.model.Portfolio;
import com.roe.almaserver.model.Syndicator;
import com.roe.almaserver.model.general.PaginatedResponse;
import com.roe.almaserver.repository.PortfolioRepository;
import com.roe.almaserver.repository.SyndicatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class SyndicatorService {

    private final SyndicatorRepository syndicatorRepository;
    private final PortfolioRepository portfolioRepository;

    @Autowired
    public SyndicatorService(SyndicatorRepository syndicatorRepository,
                             PortfolioRepository portfolioRepository) {
        this.syndicatorRepository = syndicatorRepository;
        this.portfolioRepository = portfolioRepository;
    }

    private Syndicator getSyndicatorEntity(Long id) {
        return this.syndicatorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Syndicator.class, id));
    }

    private Portfolio getPortfolioEntity(Syndicator syndicator, Long portfolioId) {
        return syndicator.getPortfolios().stream().filter(p -> p.getId().equals(portfolioId)).findFirst()
                .orElseThrow(() -> new EntityNotFoundException(Portfolio.class, portfolioId));
    }

    public SyndicatorDto getSyndicator(Long id) {
        Syndicator syndicator = getSyndicatorEntity(id);
        return Mapper.toSyndicatorDto(syndicator);
    }

    public SyndicatorDto createSyndicator(SyndicatorDto syndicatorDto) {
        Syndicator syndicator = new Syndicator().bind(syndicatorDto);
        return Mapper.toSyndicatorDto(this.syndicatorRepository.save(syndicator));
    }

    public SyndicatorDto updateSyndicator(SyndicatorDto syndicatorDto) {
        Syndicator syndicator = getSyndicatorEntity(syndicatorDto.getId());
        syndicator.bind(syndicatorDto);
        return Mapper.toSyndicatorDto(this.syndicatorRepository.save(syndicator));
    }

    public PaginatedResponse<SyndicatorDto> getSyndicatorListPage(Pageable pageable) {
        Page<Syndicator> syndicatorList = syndicatorRepository.findSyndicatorActivatedList(pageable);
        Page<SyndicatorDto> syndicatorDtoPage = new PageImpl<>(syndicatorList.getContent().stream().map(s->Mapper.toSyndicatorDto(s)).collect(Collectors.toList()));
        return new PaginatedResponse<>(syndicatorDtoPage.getContent(), (int) syndicatorDtoPage.getTotalElements());
    }

    public PortfolioDto getPortfolio(Long syndicaotrId, Long portfolioId) {
        Syndicator syndicator = getSyndicatorEntity(syndicaotrId);
        return Mapper.toPortfolioDto(getPortfolioEntity(syndicator, portfolioId));
    }

    public void addPortfolio(PortfolioDto portfolioDto) {
        Syndicator syndicator = getSyndicatorEntity(portfolioDto.getSyndicatorId());
        syndicator.getPortfolios().add(new Portfolio(syndicator).bind(portfolioDto));
        this.syndicatorRepository.save(syndicator);
    }

    public void updatePortfolio(PortfolioDto portfolioDto) {
        Syndicator syndicator = getSyndicatorEntity(portfolioDto.getSyndicatorId());
        Portfolio portfolio = getPortfolioEntity(syndicator, portfolioDto.getId());
        portfolio.bind(portfolioDto);
        this.syndicatorRepository.save(portfolio.getSyndicator());
    }
    public PaginatedResponse<PortfolioDto> getPortfolioListPage(Pageable pageable, Long syndicatorId) {
        Page<Portfolio> portfolioList = portfolioRepository.findPortfolioListPage(pageable, syndicatorId);
        Page<PortfolioDto> portfolioListDto = new PageImpl<>(portfolioList.getContent().stream().map(s->Mapper.toPortfolioDto(s)).collect(Collectors.toList()));
        return new PaginatedResponse<>(portfolioListDto.getContent(), (int) portfolioListDto.getTotalElements());
    }
    public void removeSyndicator(Long id) {
        syndicatorRepository.deleteById(id);
    }

    public void removePortfolio(Long syndicatorid, Long portfolioid) {
        Syndicator syndicator = getSyndicatorEntity(syndicatorid);
        if(syndicator.getPortfolios().stream().noneMatch(p->p.getId().equals(portfolioid)))
            throw new EntityNotFoundException(Portfolio.class, portfolioid);
        syndicator.getPortfolios().removeIf(p->p.getId().equals(portfolioid));
        this.syndicatorRepository.save(syndicator);
    }
}

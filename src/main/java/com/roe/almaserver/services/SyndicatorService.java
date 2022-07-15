package com.roe.almaserver.services;

import com.roe.almaserver.dto.PortfolioDto;
import com.roe.almaserver.exceptions.EntityNotFoundException;
import com.roe.almaserver.model.Portfolio;
import com.roe.almaserver.model.Syndicator;
import com.roe.almaserver.repository.PortfolioRepository;
import com.roe.almaserver.repository.SyndicatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.sound.sampled.Port;
import java.util.Objects;

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

    public Syndicator getSyndicator(Long id) {
        return this.syndicatorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Syndicator.class, id));
    }

    public Portfolio getPortfolio(Long syndicatorId, Long portfolioId) {
        return getSyndicator(syndicatorId).getPortfolios().stream()
                .filter(p->p.getId().equals(portfolioId))
                .findFirst().orElseThrow(() -> new EntityNotFoundException(Portfolio.class, portfolioId));
    }

    public Syndicator createSyndicator(Syndicator syndicator) {
        return this.syndicatorRepository.save(syndicator);
    }

    public Syndicator updateSyndicatorDetails(Syndicator other) {
        Syndicator syndicator = getSyndicator(other.getId());
        syndicator.setName(other.getName());
        return this.syndicatorRepository.save(syndicator);
    }

    public Page<Syndicator> getSyndicatorListPage(Pageable pageable) {
        return syndicatorRepository.findSyndicatorActivatedList(pageable);
    }

    public void addPortfolio(Long syndicatorid, Portfolio portfolio) {
        Syndicator syndicator = getSyndicator(syndicatorid);
        syndicator.getPortfolios().add(portfolio);
        portfolio.setSyndicator(syndicator);
        this.syndicatorRepository.save(syndicator);
    }

    public Portfolio updatePortfolioDetails(Long syndicatorId, PortfolioDto newPortfolio) {
        Portfolio portfolio = getPortfolio(syndicatorId, newPortfolio.getId());
        portfolio.setName(newPortfolio.getName());
        portfolio.setStatus(newPortfolio.getStatus());
        portfolio.setCustomerName(newPortfolio.getCustomerName());
        portfolio.setAmount(newPortfolio.getAmount());
        portfolio.setPriority(newPortfolio.getPriority());
        portfolio.setOfferingName(newPortfolio.getOfferingName());
        return this.portfolioRepository.save(portfolio);
    }
    public Page<Portfolio> getPortfolioListPage(Pageable pageable, Long syndicatorId) {
        return portfolioRepository.findPortfolioListPage(pageable, syndicatorId);
    }
    public void removeSyndicator(Long id) {
        syndicatorRepository.deleteById(id);
    }

    public void removePortfolio(Long syndicatorid, Long portfolioid) {
        Syndicator syndicator = getSyndicator(syndicatorid);
        Portfolio portfolio = syndicator.getPortfolios().stream()
                .filter(p->p.getId().equals(portfolioid))
                .findFirst().orElseThrow(()-> new EntityNotFoundException(Portfolio.class, portfolioid));
        syndicator.getPortfolios().remove(portfolio);
        this.syndicatorRepository.save(syndicator);
    }
}

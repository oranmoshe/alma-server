package com.roe.almaserver.services;

import com.roe.almaserver.dto.PortfolioDto;
import com.roe.almaserver.exceptions.EntityNotFoundException;
import com.roe.almaserver.exceptions.model.Portfolio;
import com.roe.almaserver.exceptions.model.Syndicator;
import com.roe.almaserver.exceptions.model.UploadedFile;
import com.roe.almaserver.repository.PortfolioRepository;
import com.roe.almaserver.repository.SyndicatorRepository;
import com.roe.almaserver.repository.UploadedFilesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

@Service
public class SyndicatorService {

    private final SyndicatorRepository syndicatorRepository;
    private final PortfolioRepository portfolioRepository;

    private final UploadedFilesRepository uploadedFilesRepository;

    private final UploadService uploadService;

    @Autowired
    public SyndicatorService(SyndicatorRepository syndicatorRepository,
                             PortfolioRepository portfolioRepository,
                             UploadedFilesRepository uploadedFilesRepository,
                             UploadService uploadService) {
        this.syndicatorRepository = syndicatorRepository;
        this.portfolioRepository = portfolioRepository;
        this.uploadedFilesRepository = uploadedFilesRepository;
        this.uploadService = uploadService;
    }

    public Syndicator getSyndicator(Long id) {
        return this.syndicatorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Syndicator.class, id));
    }

    public Portfolio getPortfolio(Long syndicatorId, Long portfolioId) {
        return this.portfolioRepository.findPortfolio(Pageable.ofSize(1), syndicatorId, portfolioId).stream().findFirst()
                .orElseThrow(() -> new EntityNotFoundException(Portfolio.class, portfolioId));
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

    public Portfolio addPortfolio(Long syndicatorid, Portfolio portfolio) {
        Syndicator syndicator = getSyndicator(syndicatorid);
        syndicator.getPortfolios().add(portfolio);
        portfolio.setSyndicator(syndicator);
        portfolio = this.portfolioRepository.save(portfolio);
        return portfolio;
    }

    public Portfolio updatePortfolioDetails(Long syndicatorId, PortfolioDto newPortfolio) {
        Portfolio portfolio = getPortfolio(syndicatorId, newPortfolio.getId());
        portfolio.setName(newPortfolio.getName());
        portfolio.setStatus(newPortfolio.getStatus());
        portfolio.setCustomerName(newPortfolio.getCustomerName());
        portfolio.setAmount(newPortfolio.getAmount());
        portfolio.setPriority(newPortfolio.getPriority());
        portfolio.setOfferingName(newPortfolio.getOfferingName());
        portfolio.setLocation(newPortfolio.getLocation());
        portfolio.setSummary(newPortfolio.getSummary());
        return this.portfolioRepository.save(portfolio);
    }
    public Page<Portfolio> getPortfolioListPage(Pageable pageable, Long syndicatorId) {
        return portfolioRepository.findBySyndicatorId(pageable, syndicatorId);
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

    public Portfolio addAttachments(Long syndicatorid, Long portfolioid, MultipartFile[] files,
                                    String uploadType) throws IOException {
        Portfolio portfolio = getPortfolio(syndicatorid, portfolioid);
        for(MultipartFile file: Arrays.asList(files)) {
            //UploadedFile uploadedFile = new UploadedFile(file, portfolio);
            String path = uploadService.storeFile(file, portfolio.getSyndicator(), portfolio);
            portfolio.addAssetAttachments(new UploadedFile(file, path, uploadType, portfolio));
        }
        return portfolioRepository.save(portfolio);
    }

    public File getFile(UploadedFile uploadedFile) throws IOException {
        return uploadService.getFile(uploadedFile);
    }

    public UploadedFile getAttachment(Long syndicatorid, Long portfolioid, Long attachmentId){
        return getPortfolio(syndicatorid, portfolioid).getUploadedFiles().stream()
                .filter(a->a.getId().equals(attachmentId)).findFirst()
                .orElseThrow(()->new EntityNotFoundException(UploadedFile.class, attachmentId));
    }

    public void removeAttachments(Long syndicatorid, Long portfolioid, Long attachmentId) {
        Portfolio portfolio = getPortfolio(syndicatorid, portfolioid);
        portfolio.removeAssetAttachments(attachmentId);
        portfolioRepository.save(portfolio);
    }
}

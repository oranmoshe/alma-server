package com.roe.almaserver.controller;

import com.roe.almaserver.dto.Converter;
import com.roe.almaserver.dto.PortfolioDto;
import com.roe.almaserver.dto.SyndicatorDto;
import com.roe.almaserver.model.Portfolio;
import com.roe.almaserver.model.Syndicator;
import com.roe.almaserver.model.UploadedFile;
import com.roe.almaserver.model.general.PaginatedResponse;
import com.roe.almaserver.repository.SyndicatorRepository;
import com.roe.almaserver.services.SyndicatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/syndicators")
public class SyndicatorController {

    private final SyndicatorService syndicatorService;
    private final SyndicatorRepository syndicatorRepository;

    @Autowired
    public SyndicatorController(SyndicatorService syndicatorService,
                                SyndicatorRepository syndicatorRepository) {
        this.syndicatorService = syndicatorService;
        this.syndicatorRepository = syndicatorRepository;
    }

    @GetMapping(path = "/{syndicatorid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public SyndicatorDto getSyndicator(@PathVariable  Long syndicatorid) {
        Syndicator syndicator = syndicatorService.getSyndicator(syndicatorid);
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
    public ResponseEntity<PortfolioDto> addPortfolio(@PathVariable Long syndicatorid, @RequestBody PortfolioDto portfolioDto) {
        Portfolio portfolio = syndicatorService.addPortfolio(syndicatorid, Converter.convertToEntity(portfolioDto));
        return new ResponseEntity<>(Converter.convertToDto(portfolio), HttpStatus.OK);
    }

    @PutMapping(path = "/{syndicatorid}/portfolio", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PortfolioDto updatePortfolioDetails(@PathVariable Long syndicatorid, @RequestBody PortfolioDto portfolioDto) {
        Portfolio portfolio = syndicatorService.updatePortfolioDetails(syndicatorid, portfolioDto);
        return Converter.convertToDto(portfolio);
    }

    @DeleteMapping(path = "/{syndicatorid}/portfolio/{portfolioid}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void removePortfolioFromSyndicator(@PathVariable Long syndicatorid, @PathVariable Long portfolioid) {
        syndicatorService.removePortfolio(syndicatorid, portfolioid);
    }

    //
    // Attachments
    //

    @PostMapping(path = "/{syndicatorid}/portfolio/{portfolioid}/attachment")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PortfolioDto addPortfolioFromSyndicator(@PathVariable Long syndicatorid, @PathVariable Long portfolioid,
                                                   @RequestParam("files") MultipartFile[] files) throws IOException {
        return Converter.convertToDto(syndicatorService.addAttachments(syndicatorid, portfolioid, files));
    }

    @DeleteMapping(path = "/{syndicatorid}/portfolio/{portfolioid}/attachment/{attachmentId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void removePortfolioFromSyndicator(@PathVariable Long syndicatorid, @PathVariable Long portfolioid,
                                              @PathVariable Long attachmentId) {
        syndicatorService.removeAttachments(syndicatorid, portfolioid, attachmentId);
    }

    @GetMapping(path = "/{syndicatorid}/portfolio/{portfolioid}/attachment/{attachmentId}", produces = MediaType.ALL_VALUE)
    public ResponseEntity<ByteArrayResource> getUploadFile(@PathVariable Long syndicatorid, @PathVariable Long portfolioid,
                                                                           @PathVariable Long attachmentId) {
        try {
            UploadedFile uploadedFile = syndicatorService.getAttachment(syndicatorid, portfolioid, attachmentId);
            File file = syndicatorService.getFile(uploadedFile);
            byte[] bytes = new byte[(int) file.length()];
            try (FileInputStream fis = new FileInputStream(file)) {
                fis.read(bytes);
                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(uploadedFile.getType()))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment:filename=\"" + file.getName() + "\"")
                        .body(new ByteArrayResource(bytes));
            }catch (Exception exc){
                return ResponseEntity.badRequest()
                        .body(new ByteArrayResource(new byte[]{}));
            }
        } catch (Exception exc) {
            return ResponseEntity.badRequest()
                    .body(new ByteArrayResource(new byte[]{}));
        }
    }
}

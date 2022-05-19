package com.roe.almaserver.dto;

import com.roe.almaserver.model.*;
import java.util.stream.Collectors;

public class Mapper {
    public static SyndicatorDto toSyndicatorDto(Syndicator syndicator) {
        return new SyndicatorDto()
                .setId(syndicator.getId())
                .setPortfolios(syndicator.getPortfolios().stream().map(Mapper::toPortfolioDto).collect(Collectors.toSet()))
                .setName(syndicator.getName());
    }

    public static PortfolioDto toPortfolioDto(Portfolio portfolio) {
        return new PortfolioDto()
                .setId(portfolio.getId())
                .setName(portfolio.getName())
                .setSyndicatorId(portfolio.getSyndicator().getId());
    }

    public static UploadedFileDto toUploadedFileDto(UploadedFile uploadedFile) {
        return new UploadedFileDto()
                .setId(uploadedFile.getId())
                .setExtension(uploadedFile.getExtension())
                .setFullName(uploadedFile.getFullName())
                .setName(uploadedFile.getName())
                .setPath(uploadedFile.getPath());
    }

    public static AssetDto toAssetDto(Asset asset){
        return new AssetDto()
                .setId(asset.getId())
                .setAssetAttachments(asset.getAssetAttachments().stream().map(Mapper::toAssetAttachmentDto).collect(Collectors.toSet()))
                .setAssetType(asset.getAssetType())
                .setEntityName(asset.getEntityName())
                .setLinkToOffering(asset.getLinkToOffering())
                .setMinimumInvestment(asset.getMinimumInvestment())
                .setName(asset.getName())
                .setOfferingSize(asset.getOfferingSize())
                .setOfferingStatus(asset.getOfferingStatus())
                .setPublicLandingPage(asset.getPublicLandingPage())
                .setShowPerRaised(asset.getShowPerRaised())
                .setStartDate(asset.getStartDate())
                .setSubscriptionType(asset.getSubscriptionType())
                .setVisibility(asset.getVisibility());
    }

    public static AssetAttachmentDto toAssetAttachmentDto(AssetAttachment assetAttachment){
        return new AssetAttachmentDto()
                .setAssetAttachmentType(assetAttachment.getAssetAttachmentType());
    }
    public static InvestorDto toInvestorDto(Investor investor) {
        return new InvestorDto()
       .setFname(investor.getFname())
       .setLname(investor.getLname())
       .setNickname(investor.getNickname())
       .setStreetAddress1(investor.getStreetAddress1())
       .setStreetAddress2(investor.getStreetAddress2())
       .setCity(investor.getCity())
       .setState(investor.getState())
       .setZipCode(investor.getZipCode())
       .setTaxId(investor.getTaxId())
       .setPhone(investor.getPhone())
       .setEmail(investor.getEmail())
       .setPassword(investor.getPassword())
       .setAccredited(investor.getAccredited())
       .setInvestmentCapacity(investor.getInvestmentCapacity())
       .setDateOfBirth(investor.getDateOfBirth())
       .setId(investor.getId());
    }


//    private Map<Platform, List<Site>> getLazilyLoadedDataForCustomer() {
//        // fetch lazily loaded data by customer, not site by site to reduce database connections and improve performance
//        Set<Site> sites = siteRepository.findSitesWithPreFetchedDefaultsSettingsAndDNs(customer);
//        Map<Platform, List<Site>> cucmSiteMap = new HashMap<>();
//        customer.getCucms().forEach(cucm ->
//                cucmSiteMap.put(cucm, sites.stream().filter(site -> site.getCucm().equals(cucm)).collect(toList())));
//        return cucmSiteMap;
//    }
}

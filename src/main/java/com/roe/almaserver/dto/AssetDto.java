package com.roe.almaserver.dto;

import com.roe.almaserver.enums.AssetType;
import com.roe.almaserver.enums.OfferingStatus;
import com.roe.almaserver.enums.SubscriptionType;
import com.roe.almaserver.enums.Visibility;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class AssetDto {
    private Long id;
    private AssetType assetType;
    private String name;
    private OfferingStatus offeringStatus;
    private SubscriptionType subscriptionType;
    private Visibility visibility;
    private String entityName;
    private Timestamp startDate;
    private String offeringSize;
    private String minimumInvestment;
    private Boolean publicLandingPage;
    private Boolean showPerRaised;
    private String linkToOffering;
    private Set<AssetAttachmentDto> assetAttachments = new HashSet<>();

    public Long getId() {
        return id;
    }

    public AssetDto setId(Long id) {
        this.id = id;
        return this;
    }

    public AssetType getAssetType() {
        return assetType;
    }

    public AssetDto setAssetType(AssetType assetType) {
        this.assetType = assetType;
        return this;
    }

    public String getName() {
        return name;
    }

    public AssetDto setName(String name) {
        this.name = name;
        return this;
    }

    public OfferingStatus getOfferingStatus() {
        return offeringStatus;
    }

    public AssetDto setOfferingStatus(OfferingStatus offeringStatus) {
        this.offeringStatus = offeringStatus;
        return this;
    }

    public SubscriptionType getSubscriptionType() {
        return subscriptionType;
    }

    public AssetDto setSubscriptionType(SubscriptionType subscriptionType) {
        this.subscriptionType = subscriptionType;
        return this;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public AssetDto setVisibility(Visibility visibility) {
        this.visibility = visibility;
        return this;
    }

    public String getEntityName() {
        return entityName;
    }

    public AssetDto setEntityName(String entityName) {
        this.entityName = entityName;
        return this;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public AssetDto setStartDate(Timestamp startDate) {
        this.startDate = startDate;
        return this;
    }

    public String getOfferingSize() {
        return offeringSize;
    }

    public AssetDto setOfferingSize(String offeringSize) {
        this.offeringSize = offeringSize;
        return this;
    }

    public String getMinimumInvestment() {
        return minimumInvestment;
    }

    public AssetDto setMinimumInvestment(String minimumInvestment) {
        this.minimumInvestment = minimumInvestment;
        return this;
    }

    public Boolean getPublicLandingPage() {
        return publicLandingPage;
    }

    public AssetDto setPublicLandingPage(Boolean publicLandingPage) {
        this.publicLandingPage = publicLandingPage;
        return this;
    }

    public Boolean getShowPerRaised() {
        return showPerRaised;
    }

    public AssetDto setShowPerRaised(Boolean showPerRaised) {
        this.showPerRaised = showPerRaised;
        return this;
    }

    public String getLinkToOffering() {
        return linkToOffering;
    }

    public AssetDto setLinkToOffering(String linkToOffering) {
        this.linkToOffering = linkToOffering;
        return this;
    }

    public Set<AssetAttachmentDto> getAssetAttachments() {
        return assetAttachments;
    }

    public AssetDto setAssetAttachments(Set<AssetAttachmentDto> assetAttachments) {
        this.assetAttachments = assetAttachments;
        return this;
    }
}

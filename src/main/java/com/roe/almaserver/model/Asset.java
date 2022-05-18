package com.roe.almaserver.model;

import com.roe.almaserver.dto.AssetType;
import com.roe.almaserver.dto.OfferingStatus;
import com.roe.almaserver.dto.SubscriptionType;
import com.roe.almaserver.dto.Visibility;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Asset")
@Table(name = "assets")
public class Asset {
    @Id
    @SequenceGenerator(name="assets_seq",sequenceName="assets_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="assets_seq")
    @Column(name = "ass_id")
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "ass_type")
    private AssetType assetType;
    @Column(name = "ass_name")
    private String name;
    @Column(name = "ass_offering_status")
    private OfferingStatus offeringStatus;
    @Column(name = "ass_subscription_type")
    private SubscriptionType subscriptionType;
    @Column(name = "ass_visibility")
    private Visibility visibility;
    @Column(name = "ass_entity_name")
    private String entityName;
    @Column(name = "ass_start_date")
    private Timestamp startDate;
    @Column(name = "ass_offering_size")
    private String offeringSize;
    @Column(name = "ass_minimum_investment")
    private String minimumInvestment;
    @Column(name = "ass_public_landing_page")
    private Boolean publicLandingPage;
    @Column(name = "ass_show_per_raised")
    private Boolean showPerRaised;
    @Column(name = "ass_link_to_offering")
    private String linkToOffering;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "asset",
            targetEntity = AssetAttachment.class, orphanRemoval = true)
    private Set<AssetAttachment> assetAttachments = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AssetType getAssetType() {
        return assetType;
    }

    public void setAssetType(AssetType assetType) {
        this.assetType = assetType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OfferingStatus getOfferingStatus() {
        return offeringStatus;
    }

    public void setOfferingStatus(OfferingStatus offeringStatus) {
        this.offeringStatus = offeringStatus;
    }

    public SubscriptionType getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(SubscriptionType subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public String getOfferingSize() {
        return offeringSize;
    }

    public void setOfferingSize(String offeringSize) {
        this.offeringSize = offeringSize;
    }

    public String getMinimumInvestment() {
        return minimumInvestment;
    }

    public void setMinimumInvestment(String minimumInvestment) {
        this.minimumInvestment = minimumInvestment;
    }

    public Boolean getPublicLandingPage() {
        return publicLandingPage;
    }

    public void setPublicLandingPage(Boolean publicLandingPage) {
        this.publicLandingPage = publicLandingPage;
    }

    public Boolean getShowPerRaised() {
        return showPerRaised;
    }

    public void setShowPerRaised(Boolean showPerRaised) {
        this.showPerRaised = showPerRaised;
    }

    public String getLinkToOffering() {
        return linkToOffering;
    }

    public void setLinkToOffering(String linkToOffering) {
        this.linkToOffering = linkToOffering;
    }

    public Set<AssetAttachment> getAssetAttachments() {
        return assetAttachments;
    }

    public void setAssetAttachments(Set<AssetAttachment> assetAttachments) {
        this.assetAttachments = assetAttachments;
    }
}

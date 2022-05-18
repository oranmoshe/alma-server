package com.roe.almaserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.roe.almaserver.enums.AssetAttachmentType;

import javax.persistence.*;

@Entity(name = "AssetAttachment")
@Table(name = "asset_attachments")
public class AssetAttachment extends UploadedFile{

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Asset.class)
    @JsonIgnore
    @JoinColumn(name = "prt_syn_id")
    private Asset asset;

    @Enumerated(EnumType.STRING)
    @Column(name="aat_type")
    AssetAttachmentType assetAttachmentType;

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    public AssetAttachmentType getAssetAttachmentType() {
        return assetAttachmentType;
    }

    public void setAssetAttachmentType(AssetAttachmentType assetAttachmentType) {
        this.assetAttachmentType = assetAttachmentType;
    }
}

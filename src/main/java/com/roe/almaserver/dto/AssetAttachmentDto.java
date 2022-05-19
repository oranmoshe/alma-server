package com.roe.almaserver.dto;

import com.roe.almaserver.enums.AssetAttachmentType;

public class AssetAttachmentDto {
    AssetAttachmentType assetAttachmentType;

    public AssetAttachmentType getAssetAttachmentType() {
        return assetAttachmentType;
    }

    public AssetAttachmentDto setAssetAttachmentType(AssetAttachmentType assetAttachmentType) {
        this.assetAttachmentType = assetAttachmentType;
        return this;
    }
}

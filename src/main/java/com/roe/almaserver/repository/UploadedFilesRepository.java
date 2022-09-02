package com.roe.almaserver.repository;

import com.roe.almaserver.exceptions.model.UploadedFile;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadedFilesRepository extends PagingAndSortingRepository<UploadedFile, Long>, JpaSpecificationExecutor<UploadedFile> {

}

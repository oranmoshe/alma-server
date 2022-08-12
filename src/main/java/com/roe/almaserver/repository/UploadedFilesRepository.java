package com.roe.almaserver.repository;

import com.roe.almaserver.model.Portfolio;
import com.roe.almaserver.model.UploadedFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UploadedFilesRepository extends PagingAndSortingRepository<UploadedFile, Long>, JpaSpecificationExecutor<UploadedFile> {

}

package com.roe.almaserver.repository;

import com.roe.almaserver.model.Portfolio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PortfolioRepository extends PagingAndSortingRepository<Portfolio, Long>, JpaSpecificationExecutor<Portfolio> {

    @Query("SELECT DISTINCT(p) from Portfolio p LEFT JOIN p.uploadedFiles where p.syndicator.id = :syndicatorId and p.id = :portfolioId ORDER BY p.name")
    Page<Portfolio> findPortfolio(Pageable pageable, Long syndicatorId, Long portfolioId);

    //@Query("SELECT DISTINCT(p) from Portfolio p  where p.syndicator.id = :syndicatorId ORDER BY p.name")
    Page<Portfolio> findBySyndicatorId(Pageable pageable, Long syndicatorId);

}

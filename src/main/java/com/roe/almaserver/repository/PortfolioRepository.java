package com.roe.almaserver.repository;

import com.roe.almaserver.model.Portfolio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortfolioRepository extends PagingAndSortingRepository<Portfolio, Long>, JpaSpecificationExecutor<Portfolio> {

    @Query("SELECT p from Portfolio p where p.syndicator.id = :syndicatorId ORDER BY p.name")
    Page<Portfolio> findPortfolioListPage(Pageable pageable, Long syndicatorId);
}

package com.roe.almaserver.repository;

import com.roe.almaserver.dto.SyndicatorDto;
import com.roe.almaserver.model.Syndicator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SyndicatorRepository extends PagingAndSortingRepository<Syndicator, Long>, JpaSpecificationExecutor<Syndicator> {
    @Query("SELECT "
            + "DISTINCT (syndicator) from Syndicator syndicator "
            + "JOIN FETCH "
            + "syndicator.portfolios portfolios "
            + "WHERE "
            + "portfolios.id = :portfolioId")
    Optional<Syndicator> findByPortfolio(Long portfolioId);

    @Query("SELECT s from Syndicator s where s.activated = TRUE ORDER BY s.name")
    Page<Syndicator> findSyndicatorActivatedList(Pageable pageable);
}

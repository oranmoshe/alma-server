package com.roe.almaserver.repository;

import com.roe.almaserver.model.Syndicator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SyndicatorRepository extends PagingAndSortingRepository<Syndicator, Long>, JpaSpecificationExecutor<Syndicator> {

    @Query("SELECT s from Syndicator s where s.activated = TRUE ORDER BY s.name")
    Page<Syndicator> findSyndicatorActivatedList(Pageable pageable);

    @Query("SELECT s from Syndicator as s " +
//            "JOIN FETCH s.portfolios as p" +
            " WHERE s.id = ?1 ")
    Optional<Syndicator> findSyndicatorEagerly(Long id);

}

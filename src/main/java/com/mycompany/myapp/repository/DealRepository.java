package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Deal;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data MongoDB reactive repository for the Deal entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DealRepository extends ReactiveMongoRepository<Deal, String> {
    Flux<Deal> findAllBy(Pageable pageable);

    @Query("{}")
    Flux<Deal> findAllWithEagerRelationships(Pageable pageable);

    @Query("{}")
    Flux<Deal> findAllWithEagerRelationships();

    @Query("{'id': ?0}")
    Mono<Deal> findOneWithEagerRelationships(String id);
}

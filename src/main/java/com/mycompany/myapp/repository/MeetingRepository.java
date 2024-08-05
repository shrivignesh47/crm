package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Meeting;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data MongoDB reactive repository for the Meeting entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MeetingRepository extends ReactiveMongoRepository<Meeting, String> {
    Flux<Meeting> findAllBy(Pageable pageable);

    @Query("{}")
    Flux<Meeting> findAllWithEagerRelationships(Pageable pageable);

    @Query("{}")
    Flux<Meeting> findAllWithEagerRelationships();

    @Query("{'id': ?0}")
    Mono<Meeting> findOneWithEagerRelationships(String id);
}

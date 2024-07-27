package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Lead;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.Lead}.
 */
public interface LeadService {
    /**
     * Save a lead.
     *
     * @param lead the entity to save.
     * @return the persisted entity.
     */
    Mono<Lead> save(Lead lead);

    /**
     * Updates a lead.
     *
     * @param lead the entity to update.
     * @return the persisted entity.
     */
    Mono<Lead> update(Lead lead);

    /**
     * Partially updates a lead.
     *
     * @param lead the entity to update partially.
     * @return the persisted entity.
     */
    Mono<Lead> partialUpdate(Lead lead);

    /**
     * Get all the leads.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<Lead> findAll(Pageable pageable);

    /**
     * Returns the number of leads available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" lead.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<Lead> findOne(String id);

    /**
     * Delete the "id" lead.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(String id);
}

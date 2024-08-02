package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Contacts;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.Contacts}.
 */
public interface ContactsService {
    /**
     * Save a contacts.
     *
     * @param contacts the entity to save.
     * @return the persisted entity.
     */
    Mono<Contacts> save(Contacts contacts);

    /**
     * Updates a contacts.
     *
     * @param contacts the entity to update.
     * @return the persisted entity.
     */
    Mono<Contacts> update(Contacts contacts);

    /**
     * Partially updates a contacts.
     *
     * @param contacts the entity to update partially.
     * @return the persisted entity.
     */
    Mono<Contacts> partialUpdate(Contacts contacts);

    /**
     * Get all the contacts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<Contacts> findAll(Pageable pageable);

    /**
     * Returns the number of contacts available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" contacts.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<Contacts> findOne(String id);

    /**
     * Delete the "id" contacts.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(String id);
}

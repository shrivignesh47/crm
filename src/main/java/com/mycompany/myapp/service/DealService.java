package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Deal;
import com.mycompany.myapp.repository.DealRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link com.mycompany.myapp.domain.Deal}.
 */
@Service
public class DealService {

    private static final Logger log = LoggerFactory.getLogger(DealService.class);

    private final DealRepository dealRepository;

    public DealService(DealRepository dealRepository) {
        this.dealRepository = dealRepository;
    }

    /**
     * Save a deal.
     *
     * @param deal the entity to save.
     * @return the persisted entity.
     */
    public Mono<Deal> save(Deal deal) {
        log.debug("Request to save Deal : {}", deal);
        return dealRepository.save(deal);
    }

    /**
     * Update a deal.
     *
     * @param deal the entity to save.
     * @return the persisted entity.
     */
    public Mono<Deal> update(Deal deal) {
        log.debug("Request to update Deal : {}", deal);
        return dealRepository.save(deal);
    }

    /**
     * Partially update a deal.
     *
     * @param deal the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<Deal> partialUpdate(Deal deal) {
        log.debug("Request to partially update Deal : {}", deal);

        return dealRepository
            .findById(deal.getId())
            .map(existingDeal -> {
                if (deal.getDeal_name() != null) {
                    existingDeal.setDeal_name(deal.getDeal_name());
                }
                if (deal.getAmount() != null) {
                    existingDeal.setAmount(deal.getAmount());
                }
                if (deal.getClosing_date() != null) {
                    existingDeal.setClosing_date(deal.getClosing_date());
                }
                if (deal.getProbability() != null) {
                    existingDeal.setProbability(deal.getProbability());
                }
                if (deal.getNext_Step() != null) {
                    existingDeal.setNext_Step(deal.getNext_Step());
                }
                if (deal.getExpected_revenue() != null) {
                    existingDeal.setExpected_revenue(deal.getExpected_revenue());
                }
                if (deal.getCampaign_source() != null) {
                    existingDeal.setCampaign_source(deal.getCampaign_source());
                }
                if (deal.getDescription_information() != null) {
                    existingDeal.setDescription_information(deal.getDescription_information());
                }
                if (deal.getStage() != null) {
                    existingDeal.setStage(deal.getStage());
                }

                return existingDeal;
            })
            .flatMap(dealRepository::save);
    }

    /**
     * Get all the deals.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Flux<Deal> findAll(Pageable pageable) {
        log.debug("Request to get all Deals");
        return dealRepository.findAllBy(pageable);
    }

    /**
     * Get all the deals with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Flux<Deal> findAllWithEagerRelationships(Pageable pageable) {
        return dealRepository.findAllWithEagerRelationships(pageable);
    }

    /**
     * Returns the number of deals available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return dealRepository.count();
    }

    /**
     * Get one deal by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Mono<Deal> findOne(String id) {
        log.debug("Request to get Deal : {}", id);
        return dealRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the deal by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(String id) {
        log.debug("Request to delete Deal : {}", id);
        return dealRepository.deleteById(id);
    }
}

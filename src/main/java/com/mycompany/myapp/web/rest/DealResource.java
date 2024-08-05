package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Deal;
import com.mycompany.myapp.repository.DealRepository;
import com.mycompany.myapp.service.DealService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.ForwardedHeaderUtils;
import reactor.core.publisher.Mono;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.reactive.ResponseUtil;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.Deal}.
 */
@RestController
@RequestMapping("/api/deals")
public class DealResource {

    private static final Logger log = LoggerFactory.getLogger(DealResource.class);

    private static final String ENTITY_NAME = "deal";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DealService dealService;

    private final DealRepository dealRepository;

    public DealResource(DealService dealService, DealRepository dealRepository) {
        this.dealService = dealService;
        this.dealRepository = dealRepository;
    }

    /**
     * {@code POST  /deals} : Create a new deal.
     *
     * @param deal the deal to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new deal, or with status {@code 400 (Bad Request)} if the deal has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public Mono<ResponseEntity<Deal>> createDeal(@Valid @RequestBody Deal deal) throws URISyntaxException {
        log.debug("REST request to save Deal : {}", deal);
        if (deal.getId() != null) {
            throw new BadRequestAlertException("A new deal cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return dealService
            .save(deal)
            .map(result -> {
                try {
                    return ResponseEntity.created(new URI("/api/deals/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /deals/:id} : Updates an existing deal.
     *
     * @param id the id of the deal to save.
     * @param deal the deal to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated deal,
     * or with status {@code 400 (Bad Request)} if the deal is not valid,
     * or with status {@code 500 (Internal Server Error)} if the deal couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<Deal>> updateDeal(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody Deal deal
    ) throws URISyntaxException {
        log.debug("REST request to update Deal : {}, {}", id, deal);
        if (deal.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, deal.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return dealRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return dealService
                    .update(deal)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(
                        result ->
                            ResponseEntity.ok()
                                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, result.getId()))
                                .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /deals/:id} : Partial updates given fields of an existing deal, field will ignore if it is null
     *
     * @param id the id of the deal to save.
     * @param deal the deal to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated deal,
     * or with status {@code 400 (Bad Request)} if the deal is not valid,
     * or with status {@code 404 (Not Found)} if the deal is not found,
     * or with status {@code 500 (Internal Server Error)} if the deal couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<Deal>> partialUpdateDeal(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody Deal deal
    ) throws URISyntaxException {
        log.debug("REST request to partial update Deal partially : {}, {}", id, deal);
        if (deal.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, deal.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return dealRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<Deal> result = dealService.partialUpdate(deal);

                return result
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(
                        res ->
                            ResponseEntity.ok()
                                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, res.getId()))
                                .body(res)
                    );
            });
    }

    /**
     * {@code GET  /deals} : get all the deals.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of deals in body.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<List<Deal>>> getAllDeals(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request,
        @RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload
    ) {
        log.debug("REST request to get a page of Deals");
        return dealService
            .countAll()
            .zipWith(dealService.findAll(pageable).collectList())
            .map(
                countWithEntities ->
                    ResponseEntity.ok()
                        .headers(
                            PaginationUtil.generatePaginationHttpHeaders(
                                ForwardedHeaderUtils.adaptFromForwardedHeaders(request.getURI(), request.getHeaders()),
                                new PageImpl<>(countWithEntities.getT2(), pageable, countWithEntities.getT1())
                            )
                        )
                        .body(countWithEntities.getT2())
            );
    }

    /**
     * {@code GET  /deals/:id} : get the "id" deal.
     *
     * @param id the id of the deal to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the deal, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<Deal>> getDeal(@PathVariable("id") String id) {
        log.debug("REST request to get Deal : {}", id);
        Mono<Deal> deal = dealService.findOne(id);
        return ResponseUtil.wrapOrNotFound(deal);
    }

    /**
     * {@code DELETE  /deals/:id} : delete the "id" deal.
     *
     * @param id the id of the deal to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteDeal(@PathVariable("id") String id) {
        log.debug("REST request to delete Deal : {}", id);
        return dealService
            .delete(id)
            .then(
                Mono.just(
                    ResponseEntity.noContent()
                        .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id))
                        .build()
                )
            );
    }
}

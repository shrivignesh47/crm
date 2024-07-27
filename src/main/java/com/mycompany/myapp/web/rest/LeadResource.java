package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Lead;
import com.mycompany.myapp.repository.LeadRepository;
import com.mycompany.myapp.service.LeadService;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.Lead}.
 */
@RestController
@RequestMapping("/api/leads")
public class LeadResource {

    private static final Logger log = LoggerFactory.getLogger(LeadResource.class);

    private static final String ENTITY_NAME = "lead";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LeadService leadService;

    private final LeadRepository leadRepository;

    public LeadResource(LeadService leadService, LeadRepository leadRepository) {
        this.leadService = leadService;
        this.leadRepository = leadRepository;
    }

    /**
     * {@code POST  /leads} : Create a new lead.
     *
     * @param lead the lead to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new lead, or with status {@code 400 (Bad Request)} if the lead has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public Mono<ResponseEntity<Lead>> createLead(@Valid @RequestBody Lead lead) throws URISyntaxException {
        log.debug("REST request to save Lead : {}", lead);
        if (lead.getId() != null) {
            throw new BadRequestAlertException("A new lead cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return leadService
            .save(lead)
            .map(result -> {
                try {
                    return ResponseEntity.created(new URI("/api/leads/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /leads/:id} : Updates an existing lead.
     *
     * @param id the id of the lead to save.
     * @param lead the lead to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated lead,
     * or with status {@code 400 (Bad Request)} if the lead is not valid,
     * or with status {@code 500 (Internal Server Error)} if the lead couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<Lead>> updateLead(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody Lead lead
    ) throws URISyntaxException {
        log.debug("REST request to update Lead : {}, {}", id, lead);
        if (lead.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, lead.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return leadRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return leadService
                    .update(lead)
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
     * {@code PATCH  /leads/:id} : Partial updates given fields of an existing lead, field will ignore if it is null
     *
     * @param id the id of the lead to save.
     * @param lead the lead to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated lead,
     * or with status {@code 400 (Bad Request)} if the lead is not valid,
     * or with status {@code 404 (Not Found)} if the lead is not found,
     * or with status {@code 500 (Internal Server Error)} if the lead couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<Lead>> partialUpdateLead(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody Lead lead
    ) throws URISyntaxException {
        log.debug("REST request to partial update Lead partially : {}, {}", id, lead);
        if (lead.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, lead.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return leadRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<Lead> result = leadService.partialUpdate(lead);

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
     * {@code GET  /leads} : get all the leads.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of leads in body.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<List<Lead>>> getAllLeads(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of Leads");
        return leadService
            .countAll()
            .zipWith(leadService.findAll(pageable).collectList())
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
     * {@code GET  /leads/:id} : get the "id" lead.
     *
     * @param id the id of the lead to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the lead, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<Lead>> getLead(@PathVariable("id") String id) {
        log.debug("REST request to get Lead : {}", id);
        Mono<Lead> lead = leadService.findOne(id);
        return ResponseUtil.wrapOrNotFound(lead);
    }

    /**
     * {@code DELETE  /leads/:id} : delete the "id" lead.
     *
     * @param id the id of the lead to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteLead(@PathVariable("id") String id) {
        log.debug("REST request to delete Lead : {}", id);
        return leadService
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

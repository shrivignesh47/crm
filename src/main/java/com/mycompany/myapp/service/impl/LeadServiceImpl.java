package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.Lead;
import com.mycompany.myapp.repository.LeadRepository;
import com.mycompany.myapp.service.LeadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link com.mycompany.myapp.domain.Lead}.
 */
@Service
public class LeadServiceImpl implements LeadService {

    private static final Logger log = LoggerFactory.getLogger(LeadServiceImpl.class);

    private final LeadRepository leadRepository;

    public LeadServiceImpl(LeadRepository leadRepository) {
        this.leadRepository = leadRepository;
    }

    @Override
    public Mono<Lead> save(Lead lead) {
        log.debug("Request to save Lead : {}", lead);
        return leadRepository.save(lead);
    }

    @Override
    public Mono<Lead> update(Lead lead) {
        log.debug("Request to update Lead : {}", lead);
        return leadRepository.save(lead);
    }

    @Override
    public Mono<Lead> partialUpdate(Lead lead) {
        log.debug("Request to partially update Lead : {}", lead);

        return leadRepository
            .findById(lead.getId())
            .map(existingLead -> {
                if (lead.getFirst_name() != null) {
                    existingLead.setFirst_name(lead.getFirst_name());
                }
                if (lead.getLast_name() != null) {
                    existingLead.setLast_name(lead.getLast_name());
                }
                if (lead.getCompany() != null) {
                    existingLead.setCompany(lead.getCompany());
                }
                if (lead.getTitle() != null) {
                    existingLead.setTitle(lead.getTitle());
                }
                if (lead.getEmail() != null) {
                    existingLead.setEmail(lead.getEmail());
                }
                if (lead.getFax() != null) {
                    existingLead.setFax(lead.getFax());
                }
                if (lead.getWebsite() != null) {
                    existingLead.setWebsite(lead.getWebsite());
                }
                if (lead.getLead_source() != null) {
                    existingLead.setLead_source(lead.getLead_source());
                }
                if (lead.getLead_status() != null) {
                    existingLead.setLead_status(lead.getLead_status());
                }
                if (lead.getIndustry() != null) {
                    existingLead.setIndustry(lead.getIndustry());
                }
                if (lead.getNo_of_emp() != null) {
                    existingLead.setNo_of_emp(lead.getNo_of_emp());
                }
                if (lead.getAnnual_revenue() != null) {
                    existingLead.setAnnual_revenue(lead.getAnnual_revenue());
                }
                if (lead.getRating() != null) {
                    existingLead.setRating(lead.getRating());
                }
                if (lead.getSocial_media() != null) {
                    existingLead.setSocial_media(lead.getSocial_media());
                }
                if (lead.getMedia_handle_id() != null) {
                    existingLead.setMedia_handle_id(lead.getMedia_handle_id());
                }
                if (lead.getStreet() != null) {
                    existingLead.setStreet(lead.getStreet());
                }
                if (lead.getCity() != null) {
                    existingLead.setCity(lead.getCity());
                }
                if (lead.getState() != null) {
                    existingLead.setState(lead.getState());
                }
                if (lead.getZipcode() != null) {
                    existingLead.setZipcode(lead.getZipcode());
                }
                if (lead.getCountry() != null) {
                    existingLead.setCountry(lead.getCountry());
                }
                if (lead.getDescription() != null) {
                    existingLead.setDescription(lead.getDescription());
                }
                if (lead.getLead_image() != null) {
                    existingLead.setLead_image(lead.getLead_image());
                }
                if (lead.getLead_imageContentType() != null) {
                    existingLead.setLead_imageContentType(lead.getLead_imageContentType());
                }
                if (lead.getPhone() != null) {
                    existingLead.setPhone(lead.getPhone());
                }

                return existingLead;
            })
            .flatMap(leadRepository::save);
    }

    @Override
    public Flux<Lead> findAll(Pageable pageable) {
        log.debug("Request to get all Leads");
        return leadRepository.findAllBy(pageable);
    }

    public Mono<Long> countAll() {
        return leadRepository.count();
    }

    @Override
    public Mono<Lead> findOne(String id) {
        log.debug("Request to get Lead : {}", id);
        return leadRepository.findById(id);
    }

    @Override
    public Mono<Void> delete(String id) {
        log.debug("Request to delete Lead : {}", id);
        return leadRepository.deleteById(id);
    }
}

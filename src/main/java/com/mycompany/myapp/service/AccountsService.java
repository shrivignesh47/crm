package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Accounts;
import com.mycompany.myapp.repository.AccountsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link com.mycompany.myapp.domain.Accounts}.
 */
@Service
public class AccountsService {

    private static final Logger log = LoggerFactory.getLogger(AccountsService.class);

    private final AccountsRepository accountsRepository;

    public AccountsService(AccountsRepository accountsRepository) {
        this.accountsRepository = accountsRepository;
    }

    /**
     * Save a accounts.
     *
     * @param accounts the entity to save.
     * @return the persisted entity.
     */
    public Mono<Accounts> save(Accounts accounts) {
        log.debug("Request to save Accounts : {}", accounts);
        return accountsRepository.save(accounts);
    }

    /**
     * Update a accounts.
     *
     * @param accounts the entity to save.
     * @return the persisted entity.
     */
    public Mono<Accounts> update(Accounts accounts) {
        log.debug("Request to update Accounts : {}", accounts);
        return accountsRepository.save(accounts);
    }

    /**
     * Partially update a accounts.
     *
     * @param accounts the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<Accounts> partialUpdate(Accounts accounts) {
        log.debug("Request to partially update Accounts : {}", accounts);

        return accountsRepository
            .findById(accounts.getId())
            .map(existingAccounts -> {
                if (accounts.getAccount_owner() != null) {
                    existingAccounts.setAccount_owner(accounts.getAccount_owner());
                }
                if (accounts.getRating() != null) {
                    existingAccounts.setRating(accounts.getRating());
                }
                if (accounts.getPhone() != null) {
                    existingAccounts.setPhone(accounts.getPhone());
                }
                if (accounts.getAccount_site() != null) {
                    existingAccounts.setAccount_site(accounts.getAccount_site());
                }
                if (accounts.getFax() != null) {
                    existingAccounts.setFax(accounts.getFax());
                }
                if (accounts.getWebsite() != null) {
                    existingAccounts.setWebsite(accounts.getWebsite());
                }
                if (accounts.getAccount_number() != null) {
                    existingAccounts.setAccount_number(accounts.getAccount_number());
                }
                if (accounts.getTicket_Symbol() != null) {
                    existingAccounts.setTicket_Symbol(accounts.getTicket_Symbol());
                }
                if (accounts.getAccount_type() != null) {
                    existingAccounts.setAccount_type(accounts.getAccount_type());
                }
                if (accounts.getOwnership() != null) {
                    existingAccounts.setOwnership(accounts.getOwnership());
                }
                if (accounts.getBilling_street() != null) {
                    existingAccounts.setBilling_street(accounts.getBilling_street());
                }
                if (accounts.getBilling_city() != null) {
                    existingAccounts.setBilling_city(accounts.getBilling_city());
                }
                if (accounts.getBilling_state() != null) {
                    existingAccounts.setBilling_state(accounts.getBilling_state());
                }
                if (accounts.getBilling_code() != null) {
                    existingAccounts.setBilling_code(accounts.getBilling_code());
                }
                if (accounts.getBilling_country() != null) {
                    existingAccounts.setBilling_country(accounts.getBilling_country());
                }
                if (accounts.getShipping_street() != null) {
                    existingAccounts.setShipping_street(accounts.getShipping_street());
                }
                if (accounts.getShipping_city() != null) {
                    existingAccounts.setShipping_city(accounts.getShipping_city());
                }
                if (accounts.getShipping_state() != null) {
                    existingAccounts.setShipping_state(accounts.getShipping_state());
                }
                if (accounts.getShipping_code() != null) {
                    existingAccounts.setShipping_code(accounts.getShipping_code());
                }
                if (accounts.getShipping_country() != null) {
                    existingAccounts.setShipping_country(accounts.getShipping_country());
                }
                if (accounts.getDescription() != null) {
                    existingAccounts.setDescription(accounts.getDescription());
                }
                if (accounts.getEmployees() != null) {
                    existingAccounts.setEmployees(accounts.getEmployees());
                }
                if (accounts.getSic_code() != null) {
                    existingAccounts.setSic_code(accounts.getSic_code());
                }

                return existingAccounts;
            })
            .flatMap(accountsRepository::save);
    }

    /**
     * Get all the accounts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Flux<Accounts> findAll(Pageable pageable) {
        log.debug("Request to get all Accounts");
        return accountsRepository.findAllBy(pageable);
    }

    /**
     * Returns the number of accounts available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return accountsRepository.count();
    }

    /**
     * Get one accounts by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Mono<Accounts> findOne(String id) {
        log.debug("Request to get Accounts : {}", id);
        return accountsRepository.findById(id);
    }

    /**
     * Delete the accounts by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(String id) {
        log.debug("Request to delete Accounts : {}", id);
        return accountsRepository.deleteById(id);
    }
}

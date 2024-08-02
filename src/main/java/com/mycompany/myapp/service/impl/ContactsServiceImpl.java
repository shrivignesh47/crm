package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.Contacts;
import com.mycompany.myapp.repository.ContactsRepository;
import com.mycompany.myapp.service.ContactsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link com.mycompany.myapp.domain.Contacts}.
 */
@Service
public class ContactsServiceImpl implements ContactsService {

    private static final Logger log = LoggerFactory.getLogger(ContactsServiceImpl.class);

    private final ContactsRepository contactsRepository;

    public ContactsServiceImpl(ContactsRepository contactsRepository) {
        this.contactsRepository = contactsRepository;
    }

    @Override
    public Mono<Contacts> save(Contacts contacts) {
        log.debug("Request to save Contacts : {}", contacts);
        return contactsRepository.save(contacts);
    }

    @Override
    public Mono<Contacts> update(Contacts contacts) {
        log.debug("Request to update Contacts : {}", contacts);
        return contactsRepository.save(contacts);
    }

    @Override
    public Mono<Contacts> partialUpdate(Contacts contacts) {
        log.debug("Request to partially update Contacts : {}", contacts);

        return contactsRepository
            .findById(contacts.getId())
            .map(existingContacts -> {
                if (contacts.getFirst_name() != null) {
                    existingContacts.setFirst_name(contacts.getFirst_name());
                }
                if (contacts.getLast_name() != null) {
                    existingContacts.setLast_name(contacts.getLast_name());
                }
                if (contacts.getAccount_name() != null) {
                    existingContacts.setAccount_name(contacts.getAccount_name());
                }
                if (contacts.getVendor_name() != null) {
                    existingContacts.setVendor_name(contacts.getVendor_name());
                }
                if (contacts.getLead_source() != null) {
                    existingContacts.setLead_source(contacts.getLead_source());
                }
                if (contacts.getEmail() != null) {
                    existingContacts.setEmail(contacts.getEmail());
                }
                if (contacts.getTitle() != null) {
                    existingContacts.setTitle(contacts.getTitle());
                }
                if (contacts.getPhone() != null) {
                    existingContacts.setPhone(contacts.getPhone());
                }
                if (contacts.getDepartment() != null) {
                    existingContacts.setDepartment(contacts.getDepartment());
                }
                if (contacts.getMobile() != null) {
                    existingContacts.setMobile(contacts.getMobile());
                }
                if (contacts.getFax() != null) {
                    existingContacts.setFax(contacts.getFax());
                }
                if (contacts.getDate_of_birth() != null) {
                    existingContacts.setDate_of_birth(contacts.getDate_of_birth());
                }
                if (contacts.getSocial_media_handle() != null) {
                    existingContacts.setSocial_media_handle(contacts.getSocial_media_handle());
                }
                if (contacts.getStreet() != null) {
                    existingContacts.setStreet(contacts.getStreet());
                }
                if (contacts.getCity() != null) {
                    existingContacts.setCity(contacts.getCity());
                }
                if (contacts.getState() != null) {
                    existingContacts.setState(contacts.getState());
                }
                if (contacts.getZip() != null) {
                    existingContacts.setZip(contacts.getZip());
                }
                if (contacts.getCountry() != null) {
                    existingContacts.setCountry(contacts.getCountry());
                }
                if (contacts.getDescription() != null) {
                    existingContacts.setDescription(contacts.getDescription());
                }

                return existingContacts;
            })
            .flatMap(contactsRepository::save);
    }

    @Override
    public Flux<Contacts> findAll(Pageable pageable) {
        log.debug("Request to get all Contacts");
        return contactsRepository.findAllBy(pageable);
    }

    public Mono<Long> countAll() {
        return contactsRepository.count();
    }

    @Override
    public Mono<Contacts> findOne(String id) {
        log.debug("Request to get Contacts : {}", id);
        return contactsRepository.findById(id);
    }

    @Override
    public Mono<Void> delete(String id) {
        log.debug("Request to delete Contacts : {}", id);
        return contactsRepository.deleteById(id);
    }
}

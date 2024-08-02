package com.mycompany.myapp.web.rest;

import static com.mycompany.myapp.domain.ContactsAsserts.*;
import static com.mycompany.myapp.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.Contacts;
import com.mycompany.myapp.domain.enumeration.source;
import com.mycompany.myapp.repository.ContactsRepository;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.UUID;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Integration tests for the {@link ContactsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class ContactsResourceIT {

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ACCOUNT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_VENDOR_NAME = "AAAAAAAAAA";
    private static final String UPDATED_VENDOR_NAME = "BBBBBBBBBB";

    private static final source DEFAULT_LEAD_SOURCE = source.NONE;
    private static final source UPDATED_LEAD_SOURCE = source.ADVERTISEMENT;

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final Long DEFAULT_PHONE = 1L;
    private static final Long UPDATED_PHONE = 2L;

    private static final String DEFAULT_DEPARTMENT = "AAAAAAAAAA";
    private static final String UPDATED_DEPARTMENT = "BBBBBBBBBB";

    private static final Long DEFAULT_MOBILE = 1L;
    private static final Long UPDATED_MOBILE = 2L;

    private static final String DEFAULT_FAX = "AAAAAAAAAA";
    private static final String UPDATED_FAX = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_OF_BIRTH = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_OF_BIRTH = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_SOCIAL_MEDIA_HANDLE = "AAAAAAAAAA";
    private static final String UPDATED_SOCIAL_MEDIA_HANDLE = "BBBBBBBBBB";

    private static final String DEFAULT_STREET = "AAAAAAAAAA";
    private static final String UPDATED_STREET = "BBBBBBBBBB";

    private static final String DEFAULT_CITY = "AAAAAAAAAA";
    private static final String UPDATED_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_STATE = "AAAAAAAAAA";
    private static final String UPDATED_STATE = "BBBBBBBBBB";

    private static final Long DEFAULT_ZIP = 1L;
    private static final Long UPDATED_ZIP = 2L;

    private static final String DEFAULT_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/contacts";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private ContactsRepository contactsRepository;

    @Autowired
    private WebTestClient webTestClient;

    private Contacts contacts;

    private Contacts insertedContacts;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Contacts createEntity() {
        Contacts contacts = new Contacts()
            .first_name(DEFAULT_FIRST_NAME)
            .last_name(DEFAULT_LAST_NAME)
            .account_name(DEFAULT_ACCOUNT_NAME)
            .vendor_name(DEFAULT_VENDOR_NAME)
            .lead_source(DEFAULT_LEAD_SOURCE)
            .email(DEFAULT_EMAIL)
            .title(DEFAULT_TITLE)
            .phone(DEFAULT_PHONE)
            .department(DEFAULT_DEPARTMENT)
            .mobile(DEFAULT_MOBILE)
            .fax(DEFAULT_FAX)
            .date_of_birth(DEFAULT_DATE_OF_BIRTH)
            .social_media_handle(DEFAULT_SOCIAL_MEDIA_HANDLE)
            .street(DEFAULT_STREET)
            .city(DEFAULT_CITY)
            .state(DEFAULT_STATE)
            .zip(DEFAULT_ZIP)
            .country(DEFAULT_COUNTRY)
            .description(DEFAULT_DESCRIPTION);
        return contacts;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Contacts createUpdatedEntity() {
        Contacts contacts = new Contacts()
            .first_name(UPDATED_FIRST_NAME)
            .last_name(UPDATED_LAST_NAME)
            .account_name(UPDATED_ACCOUNT_NAME)
            .vendor_name(UPDATED_VENDOR_NAME)
            .lead_source(UPDATED_LEAD_SOURCE)
            .email(UPDATED_EMAIL)
            .title(UPDATED_TITLE)
            .phone(UPDATED_PHONE)
            .department(UPDATED_DEPARTMENT)
            .mobile(UPDATED_MOBILE)
            .fax(UPDATED_FAX)
            .date_of_birth(UPDATED_DATE_OF_BIRTH)
            .social_media_handle(UPDATED_SOCIAL_MEDIA_HANDLE)
            .street(UPDATED_STREET)
            .city(UPDATED_CITY)
            .state(UPDATED_STATE)
            .zip(UPDATED_ZIP)
            .country(UPDATED_COUNTRY)
            .description(UPDATED_DESCRIPTION);
        return contacts;
    }

    @BeforeEach
    public void initTest() {
        contacts = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedContacts != null) {
            contactsRepository.delete(insertedContacts).block();
            insertedContacts = null;
        }
    }

    @Test
    void createContacts() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Contacts
        var returnedContacts = webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(contacts))
            .exchange()
            .expectStatus()
            .isCreated()
            .expectBody(Contacts.class)
            .returnResult()
            .getResponseBody();

        // Validate the Contacts in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertContactsUpdatableFieldsEquals(returnedContacts, getPersistedContacts(returnedContacts));

        insertedContacts = returnedContacts;
    }

    @Test
    void createContactsWithExistingId() throws Exception {
        // Create the Contacts with an existing ID
        contacts.setId("existing_id");

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(contacts))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Contacts in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    void checkFirst_nameIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        contacts.setFirst_name(null);

        // Create the Contacts, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(contacts))
            .exchange()
            .expectStatus()
            .isBadRequest();

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkLast_nameIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        contacts.setLast_name(null);

        // Create the Contacts, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(contacts))
            .exchange()
            .expectStatus()
            .isBadRequest();

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkAccount_nameIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        contacts.setAccount_name(null);

        // Create the Contacts, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(contacts))
            .exchange()
            .expectStatus()
            .isBadRequest();

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkVendor_nameIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        contacts.setVendor_name(null);

        // Create the Contacts, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(contacts))
            .exchange()
            .expectStatus()
            .isBadRequest();

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkLead_sourceIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        contacts.setLead_source(null);

        // Create the Contacts, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(contacts))
            .exchange()
            .expectStatus()
            .isBadRequest();

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkEmailIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        contacts.setEmail(null);

        // Create the Contacts, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(contacts))
            .exchange()
            .expectStatus()
            .isBadRequest();

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkTitleIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        contacts.setTitle(null);

        // Create the Contacts, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(contacts))
            .exchange()
            .expectStatus()
            .isBadRequest();

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkPhoneIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        contacts.setPhone(null);

        // Create the Contacts, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(contacts))
            .exchange()
            .expectStatus()
            .isBadRequest();

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkDepartmentIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        contacts.setDepartment(null);

        // Create the Contacts, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(contacts))
            .exchange()
            .expectStatus()
            .isBadRequest();

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkMobileIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        contacts.setMobile(null);

        // Create the Contacts, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(contacts))
            .exchange()
            .expectStatus()
            .isBadRequest();

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkDate_of_birthIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        contacts.setDate_of_birth(null);

        // Create the Contacts, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(contacts))
            .exchange()
            .expectStatus()
            .isBadRequest();

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkSocial_media_handleIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        contacts.setSocial_media_handle(null);

        // Create the Contacts, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(contacts))
            .exchange()
            .expectStatus()
            .isBadRequest();

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkStreetIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        contacts.setStreet(null);

        // Create the Contacts, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(contacts))
            .exchange()
            .expectStatus()
            .isBadRequest();

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkCityIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        contacts.setCity(null);

        // Create the Contacts, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(contacts))
            .exchange()
            .expectStatus()
            .isBadRequest();

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkStateIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        contacts.setState(null);

        // Create the Contacts, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(contacts))
            .exchange()
            .expectStatus()
            .isBadRequest();

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkZipIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        contacts.setZip(null);

        // Create the Contacts, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(contacts))
            .exchange()
            .expectStatus()
            .isBadRequest();

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkCountryIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        contacts.setCountry(null);

        // Create the Contacts, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(contacts))
            .exchange()
            .expectStatus()
            .isBadRequest();

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkDescriptionIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        contacts.setDescription(null);

        // Create the Contacts, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(contacts))
            .exchange()
            .expectStatus()
            .isBadRequest();

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void getAllContacts() {
        // Initialize the database
        insertedContacts = contactsRepository.save(contacts).block();

        // Get all the contactsList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=id,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].id")
            .value(hasItem(contacts.getId()))
            .jsonPath("$.[*].first_name")
            .value(hasItem(DEFAULT_FIRST_NAME))
            .jsonPath("$.[*].last_name")
            .value(hasItem(DEFAULT_LAST_NAME))
            .jsonPath("$.[*].account_name")
            .value(hasItem(DEFAULT_ACCOUNT_NAME))
            .jsonPath("$.[*].vendor_name")
            .value(hasItem(DEFAULT_VENDOR_NAME))
            .jsonPath("$.[*].lead_source")
            .value(hasItem(DEFAULT_LEAD_SOURCE.toString()))
            .jsonPath("$.[*].email")
            .value(hasItem(DEFAULT_EMAIL))
            .jsonPath("$.[*].title")
            .value(hasItem(DEFAULT_TITLE))
            .jsonPath("$.[*].phone")
            .value(hasItem(DEFAULT_PHONE.intValue()))
            .jsonPath("$.[*].department")
            .value(hasItem(DEFAULT_DEPARTMENT))
            .jsonPath("$.[*].mobile")
            .value(hasItem(DEFAULT_MOBILE.intValue()))
            .jsonPath("$.[*].fax")
            .value(hasItem(DEFAULT_FAX))
            .jsonPath("$.[*].date_of_birth")
            .value(hasItem(DEFAULT_DATE_OF_BIRTH.toString()))
            .jsonPath("$.[*].social_media_handle")
            .value(hasItem(DEFAULT_SOCIAL_MEDIA_HANDLE))
            .jsonPath("$.[*].street")
            .value(hasItem(DEFAULT_STREET))
            .jsonPath("$.[*].city")
            .value(hasItem(DEFAULT_CITY))
            .jsonPath("$.[*].state")
            .value(hasItem(DEFAULT_STATE))
            .jsonPath("$.[*].zip")
            .value(hasItem(DEFAULT_ZIP.intValue()))
            .jsonPath("$.[*].country")
            .value(hasItem(DEFAULT_COUNTRY))
            .jsonPath("$.[*].description")
            .value(hasItem(DEFAULT_DESCRIPTION));
    }

    @Test
    void getContacts() {
        // Initialize the database
        insertedContacts = contactsRepository.save(contacts).block();

        // Get the contacts
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, contacts.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(contacts.getId()))
            .jsonPath("$.first_name")
            .value(is(DEFAULT_FIRST_NAME))
            .jsonPath("$.last_name")
            .value(is(DEFAULT_LAST_NAME))
            .jsonPath("$.account_name")
            .value(is(DEFAULT_ACCOUNT_NAME))
            .jsonPath("$.vendor_name")
            .value(is(DEFAULT_VENDOR_NAME))
            .jsonPath("$.lead_source")
            .value(is(DEFAULT_LEAD_SOURCE.toString()))
            .jsonPath("$.email")
            .value(is(DEFAULT_EMAIL))
            .jsonPath("$.title")
            .value(is(DEFAULT_TITLE))
            .jsonPath("$.phone")
            .value(is(DEFAULT_PHONE.intValue()))
            .jsonPath("$.department")
            .value(is(DEFAULT_DEPARTMENT))
            .jsonPath("$.mobile")
            .value(is(DEFAULT_MOBILE.intValue()))
            .jsonPath("$.fax")
            .value(is(DEFAULT_FAX))
            .jsonPath("$.date_of_birth")
            .value(is(DEFAULT_DATE_OF_BIRTH.toString()))
            .jsonPath("$.social_media_handle")
            .value(is(DEFAULT_SOCIAL_MEDIA_HANDLE))
            .jsonPath("$.street")
            .value(is(DEFAULT_STREET))
            .jsonPath("$.city")
            .value(is(DEFAULT_CITY))
            .jsonPath("$.state")
            .value(is(DEFAULT_STATE))
            .jsonPath("$.zip")
            .value(is(DEFAULT_ZIP.intValue()))
            .jsonPath("$.country")
            .value(is(DEFAULT_COUNTRY))
            .jsonPath("$.description")
            .value(is(DEFAULT_DESCRIPTION));
    }

    @Test
    void getNonExistingContacts() {
        // Get the contacts
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_PROBLEM_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingContacts() throws Exception {
        // Initialize the database
        insertedContacts = contactsRepository.save(contacts).block();

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the contacts
        Contacts updatedContacts = contactsRepository.findById(contacts.getId()).block();
        updatedContacts
            .first_name(UPDATED_FIRST_NAME)
            .last_name(UPDATED_LAST_NAME)
            .account_name(UPDATED_ACCOUNT_NAME)
            .vendor_name(UPDATED_VENDOR_NAME)
            .lead_source(UPDATED_LEAD_SOURCE)
            .email(UPDATED_EMAIL)
            .title(UPDATED_TITLE)
            .phone(UPDATED_PHONE)
            .department(UPDATED_DEPARTMENT)
            .mobile(UPDATED_MOBILE)
            .fax(UPDATED_FAX)
            .date_of_birth(UPDATED_DATE_OF_BIRTH)
            .social_media_handle(UPDATED_SOCIAL_MEDIA_HANDLE)
            .street(UPDATED_STREET)
            .city(UPDATED_CITY)
            .state(UPDATED_STATE)
            .zip(UPDATED_ZIP)
            .country(UPDATED_COUNTRY)
            .description(UPDATED_DESCRIPTION);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, updatedContacts.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(updatedContacts))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Contacts in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedContactsToMatchAllProperties(updatedContacts);
    }

    @Test
    void putNonExistingContacts() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        contacts.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, contacts.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(contacts))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Contacts in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchContacts() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        contacts.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, UUID.randomUUID().toString())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(contacts))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Contacts in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamContacts() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        contacts.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(contacts))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the Contacts in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateContactsWithPatch() throws Exception {
        // Initialize the database
        insertedContacts = contactsRepository.save(contacts).block();

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the contacts using partial update
        Contacts partialUpdatedContacts = new Contacts();
        partialUpdatedContacts.setId(contacts.getId());

        partialUpdatedContacts
            .first_name(UPDATED_FIRST_NAME)
            .last_name(UPDATED_LAST_NAME)
            .account_name(UPDATED_ACCOUNT_NAME)
            .lead_source(UPDATED_LEAD_SOURCE)
            .email(UPDATED_EMAIL)
            .title(UPDATED_TITLE)
            .phone(UPDATED_PHONE)
            .city(UPDATED_CITY)
            .state(UPDATED_STATE)
            .zip(UPDATED_ZIP);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedContacts.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(om.writeValueAsBytes(partialUpdatedContacts))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Contacts in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertContactsUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedContacts, contacts), getPersistedContacts(contacts));
    }

    @Test
    void fullUpdateContactsWithPatch() throws Exception {
        // Initialize the database
        insertedContacts = contactsRepository.save(contacts).block();

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the contacts using partial update
        Contacts partialUpdatedContacts = new Contacts();
        partialUpdatedContacts.setId(contacts.getId());

        partialUpdatedContacts
            .first_name(UPDATED_FIRST_NAME)
            .last_name(UPDATED_LAST_NAME)
            .account_name(UPDATED_ACCOUNT_NAME)
            .vendor_name(UPDATED_VENDOR_NAME)
            .lead_source(UPDATED_LEAD_SOURCE)
            .email(UPDATED_EMAIL)
            .title(UPDATED_TITLE)
            .phone(UPDATED_PHONE)
            .department(UPDATED_DEPARTMENT)
            .mobile(UPDATED_MOBILE)
            .fax(UPDATED_FAX)
            .date_of_birth(UPDATED_DATE_OF_BIRTH)
            .social_media_handle(UPDATED_SOCIAL_MEDIA_HANDLE)
            .street(UPDATED_STREET)
            .city(UPDATED_CITY)
            .state(UPDATED_STATE)
            .zip(UPDATED_ZIP)
            .country(UPDATED_COUNTRY)
            .description(UPDATED_DESCRIPTION);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedContacts.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(om.writeValueAsBytes(partialUpdatedContacts))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Contacts in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertContactsUpdatableFieldsEquals(partialUpdatedContacts, getPersistedContacts(partialUpdatedContacts));
    }

    @Test
    void patchNonExistingContacts() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        contacts.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, contacts.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(om.writeValueAsBytes(contacts))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Contacts in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchContacts() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        contacts.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, UUID.randomUUID().toString())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(om.writeValueAsBytes(contacts))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Contacts in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamContacts() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        contacts.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(om.writeValueAsBytes(contacts))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the Contacts in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteContacts() {
        // Initialize the database
        insertedContacts = contactsRepository.save(contacts).block();

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the contacts
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, contacts.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return contactsRepository.count().block();
    }

    protected void assertIncrementedRepositoryCount(long countBefore) {
        assertThat(countBefore + 1).isEqualTo(getRepositoryCount());
    }

    protected void assertDecrementedRepositoryCount(long countBefore) {
        assertThat(countBefore - 1).isEqualTo(getRepositoryCount());
    }

    protected void assertSameRepositoryCount(long countBefore) {
        assertThat(countBefore).isEqualTo(getRepositoryCount());
    }

    protected Contacts getPersistedContacts(Contacts contacts) {
        return contactsRepository.findById(contacts.getId()).block();
    }

    protected void assertPersistedContactsToMatchAllProperties(Contacts expectedContacts) {
        assertContactsAllPropertiesEquals(expectedContacts, getPersistedContacts(expectedContacts));
    }

    protected void assertPersistedContactsToMatchUpdatableProperties(Contacts expectedContacts) {
        assertContactsAllUpdatablePropertiesEquals(expectedContacts, getPersistedContacts(expectedContacts));
    }
}

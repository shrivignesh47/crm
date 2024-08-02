package com.mycompany.myapp.web.rest;

import static com.mycompany.myapp.domain.AccountsAsserts.*;
import static com.mycompany.myapp.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.Accounts;
import com.mycompany.myapp.domain.enumeration.ownership;
import com.mycompany.myapp.domain.enumeration.rating;
import com.mycompany.myapp.domain.enumeration.type;
import com.mycompany.myapp.repository.AccountsRepository;
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
 * Integration tests for the {@link AccountsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class AccountsResourceIT {

    private static final String DEFAULT_ACCOUNT_OWNER = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNT_OWNER = "BBBBBBBBBB";

    private static final rating DEFAULT_RATING = rating.NONE;
    private static final rating UPDATED_RATING = rating.ACQUIRED;

    private static final Long DEFAULT_PHONE = 1L;
    private static final Long UPDATED_PHONE = 2L;

    private static final String DEFAULT_ACCOUNT_SITE = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNT_SITE = "BBBBBBBBBB";

    private static final String DEFAULT_FAX = "AAAAAAAAAA";
    private static final String UPDATED_FAX = "BBBBBBBBBB";

    private static final String DEFAULT_WEBSITE = "AAAAAAAAAA";
    private static final String UPDATED_WEBSITE = "BBBBBBBBBB";

    private static final Long DEFAULT_ACCOUNT_NUMBER = 1L;
    private static final Long UPDATED_ACCOUNT_NUMBER = 2L;

    private static final String DEFAULT_TICKET_SYMBOL = "AAAAAAAAAA";
    private static final String UPDATED_TICKET_SYMBOL = "BBBBBBBBBB";

    private static final type DEFAULT_ACCOUNT_TYPE = type.NONE;
    private static final type UPDATED_ACCOUNT_TYPE = type.ANALYST;

    private static final ownership DEFAULT_OWNERSHIP = ownership.NONE;
    private static final ownership UPDATED_OWNERSHIP = ownership.OTHER;

    private static final String DEFAULT_BILLING_STREET = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_STREET = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_CITY = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_STATE = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_CODE = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_SHIPPING_STREET = "AAAAAAAAAA";
    private static final String UPDATED_SHIPPING_STREET = "BBBBBBBBBB";

    private static final String DEFAULT_SHIPPING_CITY = "AAAAAAAAAA";
    private static final String UPDATED_SHIPPING_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_SHIPPING_STATE = "AAAAAAAAAA";
    private static final String UPDATED_SHIPPING_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_SHIPPING_CODE = "AAAAAAAAAA";
    private static final String UPDATED_SHIPPING_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_SHIPPING_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_SHIPPING_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_EMPLOYEES = 1;
    private static final Integer UPDATED_EMPLOYEES = 2;

    private static final Long DEFAULT_SIC_CODE = 1L;
    private static final Long UPDATED_SIC_CODE = 2L;

    private static final String ENTITY_API_URL = "/api/accounts";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private AccountsRepository accountsRepository;

    @Autowired
    private WebTestClient webTestClient;

    private Accounts accounts;

    private Accounts insertedAccounts;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Accounts createEntity() {
        Accounts accounts = new Accounts()
            .account_owner(DEFAULT_ACCOUNT_OWNER)
            .rating(DEFAULT_RATING)
            .phone(DEFAULT_PHONE)
            .account_site(DEFAULT_ACCOUNT_SITE)
            .fax(DEFAULT_FAX)
            .website(DEFAULT_WEBSITE)
            .account_number(DEFAULT_ACCOUNT_NUMBER)
            .ticket_Symbol(DEFAULT_TICKET_SYMBOL)
            .account_type(DEFAULT_ACCOUNT_TYPE)
            .ownership(DEFAULT_OWNERSHIP)
            .billing_street(DEFAULT_BILLING_STREET)
            .billing_city(DEFAULT_BILLING_CITY)
            .billing_state(DEFAULT_BILLING_STATE)
            .billing_code(DEFAULT_BILLING_CODE)
            .billing_country(DEFAULT_BILLING_COUNTRY)
            .shipping_street(DEFAULT_SHIPPING_STREET)
            .shipping_city(DEFAULT_SHIPPING_CITY)
            .shipping_state(DEFAULT_SHIPPING_STATE)
            .shipping_code(DEFAULT_SHIPPING_CODE)
            .shipping_country(DEFAULT_SHIPPING_COUNTRY)
            .description(DEFAULT_DESCRIPTION)
            .employees(DEFAULT_EMPLOYEES)
            .sic_code(DEFAULT_SIC_CODE);
        return accounts;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Accounts createUpdatedEntity() {
        Accounts accounts = new Accounts()
            .account_owner(UPDATED_ACCOUNT_OWNER)
            .rating(UPDATED_RATING)
            .phone(UPDATED_PHONE)
            .account_site(UPDATED_ACCOUNT_SITE)
            .fax(UPDATED_FAX)
            .website(UPDATED_WEBSITE)
            .account_number(UPDATED_ACCOUNT_NUMBER)
            .ticket_Symbol(UPDATED_TICKET_SYMBOL)
            .account_type(UPDATED_ACCOUNT_TYPE)
            .ownership(UPDATED_OWNERSHIP)
            .billing_street(UPDATED_BILLING_STREET)
            .billing_city(UPDATED_BILLING_CITY)
            .billing_state(UPDATED_BILLING_STATE)
            .billing_code(UPDATED_BILLING_CODE)
            .billing_country(UPDATED_BILLING_COUNTRY)
            .shipping_street(UPDATED_SHIPPING_STREET)
            .shipping_city(UPDATED_SHIPPING_CITY)
            .shipping_state(UPDATED_SHIPPING_STATE)
            .shipping_code(UPDATED_SHIPPING_CODE)
            .shipping_country(UPDATED_SHIPPING_COUNTRY)
            .description(UPDATED_DESCRIPTION)
            .employees(UPDATED_EMPLOYEES)
            .sic_code(UPDATED_SIC_CODE);
        return accounts;
    }

    @BeforeEach
    public void initTest() {
        accounts = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedAccounts != null) {
            accountsRepository.delete(insertedAccounts).block();
            insertedAccounts = null;
        }
    }

    @Test
    void createAccounts() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Accounts
        var returnedAccounts = webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(accounts))
            .exchange()
            .expectStatus()
            .isCreated()
            .expectBody(Accounts.class)
            .returnResult()
            .getResponseBody();

        // Validate the Accounts in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertAccountsUpdatableFieldsEquals(returnedAccounts, getPersistedAccounts(returnedAccounts));

        insertedAccounts = returnedAccounts;
    }

    @Test
    void createAccountsWithExistingId() throws Exception {
        // Create the Accounts with an existing ID
        accounts.setId("existing_id");

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(accounts))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Accounts in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    void checkAccount_ownerIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        accounts.setAccount_owner(null);

        // Create the Accounts, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(accounts))
            .exchange()
            .expectStatus()
            .isBadRequest();

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkRatingIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        accounts.setRating(null);

        // Create the Accounts, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(accounts))
            .exchange()
            .expectStatus()
            .isBadRequest();

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkPhoneIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        accounts.setPhone(null);

        // Create the Accounts, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(accounts))
            .exchange()
            .expectStatus()
            .isBadRequest();

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkAccount_siteIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        accounts.setAccount_site(null);

        // Create the Accounts, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(accounts))
            .exchange()
            .expectStatus()
            .isBadRequest();

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkWebsiteIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        accounts.setWebsite(null);

        // Create the Accounts, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(accounts))
            .exchange()
            .expectStatus()
            .isBadRequest();

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkAccount_numberIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        accounts.setAccount_number(null);

        // Create the Accounts, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(accounts))
            .exchange()
            .expectStatus()
            .isBadRequest();

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkAccount_typeIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        accounts.setAccount_type(null);

        // Create the Accounts, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(accounts))
            .exchange()
            .expectStatus()
            .isBadRequest();

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkOwnershipIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        accounts.setOwnership(null);

        // Create the Accounts, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(accounts))
            .exchange()
            .expectStatus()
            .isBadRequest();

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkBilling_streetIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        accounts.setBilling_street(null);

        // Create the Accounts, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(accounts))
            .exchange()
            .expectStatus()
            .isBadRequest();

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkBilling_cityIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        accounts.setBilling_city(null);

        // Create the Accounts, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(accounts))
            .exchange()
            .expectStatus()
            .isBadRequest();

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkBilling_stateIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        accounts.setBilling_state(null);

        // Create the Accounts, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(accounts))
            .exchange()
            .expectStatus()
            .isBadRequest();

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkBilling_codeIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        accounts.setBilling_code(null);

        // Create the Accounts, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(accounts))
            .exchange()
            .expectStatus()
            .isBadRequest();

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkBilling_countryIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        accounts.setBilling_country(null);

        // Create the Accounts, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(accounts))
            .exchange()
            .expectStatus()
            .isBadRequest();

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkShipping_streetIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        accounts.setShipping_street(null);

        // Create the Accounts, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(accounts))
            .exchange()
            .expectStatus()
            .isBadRequest();

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkDescriptionIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        accounts.setDescription(null);

        // Create the Accounts, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(accounts))
            .exchange()
            .expectStatus()
            .isBadRequest();

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkEmployeesIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        accounts.setEmployees(null);

        // Create the Accounts, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(accounts))
            .exchange()
            .expectStatus()
            .isBadRequest();

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void getAllAccounts() {
        // Initialize the database
        insertedAccounts = accountsRepository.save(accounts).block();

        // Get all the accountsList
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
            .value(hasItem(accounts.getId()))
            .jsonPath("$.[*].account_owner")
            .value(hasItem(DEFAULT_ACCOUNT_OWNER))
            .jsonPath("$.[*].rating")
            .value(hasItem(DEFAULT_RATING.toString()))
            .jsonPath("$.[*].phone")
            .value(hasItem(DEFAULT_PHONE.intValue()))
            .jsonPath("$.[*].account_site")
            .value(hasItem(DEFAULT_ACCOUNT_SITE))
            .jsonPath("$.[*].fax")
            .value(hasItem(DEFAULT_FAX))
            .jsonPath("$.[*].website")
            .value(hasItem(DEFAULT_WEBSITE))
            .jsonPath("$.[*].account_number")
            .value(hasItem(DEFAULT_ACCOUNT_NUMBER.intValue()))
            .jsonPath("$.[*].ticket_Symbol")
            .value(hasItem(DEFAULT_TICKET_SYMBOL))
            .jsonPath("$.[*].account_type")
            .value(hasItem(DEFAULT_ACCOUNT_TYPE.toString()))
            .jsonPath("$.[*].ownership")
            .value(hasItem(DEFAULT_OWNERSHIP.toString()))
            .jsonPath("$.[*].billing_street")
            .value(hasItem(DEFAULT_BILLING_STREET))
            .jsonPath("$.[*].billing_city")
            .value(hasItem(DEFAULT_BILLING_CITY))
            .jsonPath("$.[*].billing_state")
            .value(hasItem(DEFAULT_BILLING_STATE))
            .jsonPath("$.[*].billing_code")
            .value(hasItem(DEFAULT_BILLING_CODE))
            .jsonPath("$.[*].billing_country")
            .value(hasItem(DEFAULT_BILLING_COUNTRY))
            .jsonPath("$.[*].shipping_street")
            .value(hasItem(DEFAULT_SHIPPING_STREET))
            .jsonPath("$.[*].shipping_city")
            .value(hasItem(DEFAULT_SHIPPING_CITY))
            .jsonPath("$.[*].shipping_state")
            .value(hasItem(DEFAULT_SHIPPING_STATE))
            .jsonPath("$.[*].shipping_code")
            .value(hasItem(DEFAULT_SHIPPING_CODE))
            .jsonPath("$.[*].shipping_country")
            .value(hasItem(DEFAULT_SHIPPING_COUNTRY))
            .jsonPath("$.[*].description")
            .value(hasItem(DEFAULT_DESCRIPTION))
            .jsonPath("$.[*].employees")
            .value(hasItem(DEFAULT_EMPLOYEES))
            .jsonPath("$.[*].sic_code")
            .value(hasItem(DEFAULT_SIC_CODE.intValue()));
    }

    @Test
    void getAccounts() {
        // Initialize the database
        insertedAccounts = accountsRepository.save(accounts).block();

        // Get the accounts
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, accounts.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(accounts.getId()))
            .jsonPath("$.account_owner")
            .value(is(DEFAULT_ACCOUNT_OWNER))
            .jsonPath("$.rating")
            .value(is(DEFAULT_RATING.toString()))
            .jsonPath("$.phone")
            .value(is(DEFAULT_PHONE.intValue()))
            .jsonPath("$.account_site")
            .value(is(DEFAULT_ACCOUNT_SITE))
            .jsonPath("$.fax")
            .value(is(DEFAULT_FAX))
            .jsonPath("$.website")
            .value(is(DEFAULT_WEBSITE))
            .jsonPath("$.account_number")
            .value(is(DEFAULT_ACCOUNT_NUMBER.intValue()))
            .jsonPath("$.ticket_Symbol")
            .value(is(DEFAULT_TICKET_SYMBOL))
            .jsonPath("$.account_type")
            .value(is(DEFAULT_ACCOUNT_TYPE.toString()))
            .jsonPath("$.ownership")
            .value(is(DEFAULT_OWNERSHIP.toString()))
            .jsonPath("$.billing_street")
            .value(is(DEFAULT_BILLING_STREET))
            .jsonPath("$.billing_city")
            .value(is(DEFAULT_BILLING_CITY))
            .jsonPath("$.billing_state")
            .value(is(DEFAULT_BILLING_STATE))
            .jsonPath("$.billing_code")
            .value(is(DEFAULT_BILLING_CODE))
            .jsonPath("$.billing_country")
            .value(is(DEFAULT_BILLING_COUNTRY))
            .jsonPath("$.shipping_street")
            .value(is(DEFAULT_SHIPPING_STREET))
            .jsonPath("$.shipping_city")
            .value(is(DEFAULT_SHIPPING_CITY))
            .jsonPath("$.shipping_state")
            .value(is(DEFAULT_SHIPPING_STATE))
            .jsonPath("$.shipping_code")
            .value(is(DEFAULT_SHIPPING_CODE))
            .jsonPath("$.shipping_country")
            .value(is(DEFAULT_SHIPPING_COUNTRY))
            .jsonPath("$.description")
            .value(is(DEFAULT_DESCRIPTION))
            .jsonPath("$.employees")
            .value(is(DEFAULT_EMPLOYEES))
            .jsonPath("$.sic_code")
            .value(is(DEFAULT_SIC_CODE.intValue()));
    }

    @Test
    void getNonExistingAccounts() {
        // Get the accounts
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_PROBLEM_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingAccounts() throws Exception {
        // Initialize the database
        insertedAccounts = accountsRepository.save(accounts).block();

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the accounts
        Accounts updatedAccounts = accountsRepository.findById(accounts.getId()).block();
        updatedAccounts
            .account_owner(UPDATED_ACCOUNT_OWNER)
            .rating(UPDATED_RATING)
            .phone(UPDATED_PHONE)
            .account_site(UPDATED_ACCOUNT_SITE)
            .fax(UPDATED_FAX)
            .website(UPDATED_WEBSITE)
            .account_number(UPDATED_ACCOUNT_NUMBER)
            .ticket_Symbol(UPDATED_TICKET_SYMBOL)
            .account_type(UPDATED_ACCOUNT_TYPE)
            .ownership(UPDATED_OWNERSHIP)
            .billing_street(UPDATED_BILLING_STREET)
            .billing_city(UPDATED_BILLING_CITY)
            .billing_state(UPDATED_BILLING_STATE)
            .billing_code(UPDATED_BILLING_CODE)
            .billing_country(UPDATED_BILLING_COUNTRY)
            .shipping_street(UPDATED_SHIPPING_STREET)
            .shipping_city(UPDATED_SHIPPING_CITY)
            .shipping_state(UPDATED_SHIPPING_STATE)
            .shipping_code(UPDATED_SHIPPING_CODE)
            .shipping_country(UPDATED_SHIPPING_COUNTRY)
            .description(UPDATED_DESCRIPTION)
            .employees(UPDATED_EMPLOYEES)
            .sic_code(UPDATED_SIC_CODE);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, updatedAccounts.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(updatedAccounts))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Accounts in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedAccountsToMatchAllProperties(updatedAccounts);
    }

    @Test
    void putNonExistingAccounts() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        accounts.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, accounts.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(accounts))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Accounts in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchAccounts() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        accounts.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, UUID.randomUUID().toString())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(accounts))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Accounts in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamAccounts() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        accounts.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(accounts))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the Accounts in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateAccountsWithPatch() throws Exception {
        // Initialize the database
        insertedAccounts = accountsRepository.save(accounts).block();

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the accounts using partial update
        Accounts partialUpdatedAccounts = new Accounts();
        partialUpdatedAccounts.setId(accounts.getId());

        partialUpdatedAccounts
            .account_site(UPDATED_ACCOUNT_SITE)
            .website(UPDATED_WEBSITE)
            .account_number(UPDATED_ACCOUNT_NUMBER)
            .account_type(UPDATED_ACCOUNT_TYPE)
            .ownership(UPDATED_OWNERSHIP)
            .billing_street(UPDATED_BILLING_STREET)
            .billing_city(UPDATED_BILLING_CITY)
            .billing_code(UPDATED_BILLING_CODE)
            .shipping_city(UPDATED_SHIPPING_CITY)
            .shipping_state(UPDATED_SHIPPING_STATE);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedAccounts.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(om.writeValueAsBytes(partialUpdatedAccounts))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Accounts in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertAccountsUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedAccounts, accounts), getPersistedAccounts(accounts));
    }

    @Test
    void fullUpdateAccountsWithPatch() throws Exception {
        // Initialize the database
        insertedAccounts = accountsRepository.save(accounts).block();

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the accounts using partial update
        Accounts partialUpdatedAccounts = new Accounts();
        partialUpdatedAccounts.setId(accounts.getId());

        partialUpdatedAccounts
            .account_owner(UPDATED_ACCOUNT_OWNER)
            .rating(UPDATED_RATING)
            .phone(UPDATED_PHONE)
            .account_site(UPDATED_ACCOUNT_SITE)
            .fax(UPDATED_FAX)
            .website(UPDATED_WEBSITE)
            .account_number(UPDATED_ACCOUNT_NUMBER)
            .ticket_Symbol(UPDATED_TICKET_SYMBOL)
            .account_type(UPDATED_ACCOUNT_TYPE)
            .ownership(UPDATED_OWNERSHIP)
            .billing_street(UPDATED_BILLING_STREET)
            .billing_city(UPDATED_BILLING_CITY)
            .billing_state(UPDATED_BILLING_STATE)
            .billing_code(UPDATED_BILLING_CODE)
            .billing_country(UPDATED_BILLING_COUNTRY)
            .shipping_street(UPDATED_SHIPPING_STREET)
            .shipping_city(UPDATED_SHIPPING_CITY)
            .shipping_state(UPDATED_SHIPPING_STATE)
            .shipping_code(UPDATED_SHIPPING_CODE)
            .shipping_country(UPDATED_SHIPPING_COUNTRY)
            .description(UPDATED_DESCRIPTION)
            .employees(UPDATED_EMPLOYEES)
            .sic_code(UPDATED_SIC_CODE);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedAccounts.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(om.writeValueAsBytes(partialUpdatedAccounts))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Accounts in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertAccountsUpdatableFieldsEquals(partialUpdatedAccounts, getPersistedAccounts(partialUpdatedAccounts));
    }

    @Test
    void patchNonExistingAccounts() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        accounts.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, accounts.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(om.writeValueAsBytes(accounts))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Accounts in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchAccounts() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        accounts.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, UUID.randomUUID().toString())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(om.writeValueAsBytes(accounts))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Accounts in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamAccounts() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        accounts.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(om.writeValueAsBytes(accounts))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the Accounts in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteAccounts() {
        // Initialize the database
        insertedAccounts = accountsRepository.save(accounts).block();

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the accounts
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, accounts.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return accountsRepository.count().block();
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

    protected Accounts getPersistedAccounts(Accounts accounts) {
        return accountsRepository.findById(accounts.getId()).block();
    }

    protected void assertPersistedAccountsToMatchAllProperties(Accounts expectedAccounts) {
        assertAccountsAllPropertiesEquals(expectedAccounts, getPersistedAccounts(expectedAccounts));
    }

    protected void assertPersistedAccountsToMatchUpdatableProperties(Accounts expectedAccounts) {
        assertAccountsAllUpdatablePropertiesEquals(expectedAccounts, getPersistedAccounts(expectedAccounts));
    }
}

package com.mycompany.myapp.web.rest;

import static com.mycompany.myapp.domain.MeetingAsserts.*;
import static com.mycompany.myapp.web.rest.TestUtil.createUpdateProxyForBean;
import static com.mycompany.myapp.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.Meeting;
import com.mycompany.myapp.domain.User;
import com.mycompany.myapp.domain.enumeration.location;
import com.mycompany.myapp.domain.enumeration.participants;
import com.mycompany.myapp.repository.MeetingRepository;
import com.mycompany.myapp.repository.UserRepository;
import com.mycompany.myapp.service.MeetingService;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

/**
 * Integration tests for the {@link MeetingResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class MeetingResourceIT {

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final location DEFAULT_LOCATION = location.ONLINE;
    private static final location UPDATED_LOCATION = location.OFFLINE;

    private static final String DEFAULT_OFFLINE_LOCATION = "AAAAAAAAAA";
    private static final String UPDATED_OFFLINE_LOCATION = "BBBBBBBBBB";

    private static final Instant DEFAULT_CURRENT_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CURRENT_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final ZonedDateTime DEFAULT_FROM = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_FROM = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_TO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_TO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final participants DEFAULT_PARTICIPANTS = participants.OFFLINE;
    private static final participants UPDATED_PARTICIPANTS = participants.CONTACTS;

    private static final String ENTITY_API_URL = "/api/meetings";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private MeetingRepository meetingRepository;

    @Autowired
    private UserRepository userRepository;

    @Mock
    private MeetingRepository meetingRepositoryMock;

    @Mock
    private MeetingService meetingServiceMock;

    @Autowired
    private WebTestClient webTestClient;

    private Meeting meeting;

    private Meeting insertedMeeting;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Meeting createEntity() {
        Meeting meeting = new Meeting()
            .title(DEFAULT_TITLE)
            .location(DEFAULT_LOCATION)
            .offline_location(DEFAULT_OFFLINE_LOCATION)
            .current_time(DEFAULT_CURRENT_TIME)
            .from(DEFAULT_FROM)
            .to(DEFAULT_TO)
            .participants(DEFAULT_PARTICIPANTS);
        // Add required entity
        User user = UserResourceIT.createEntity();
        user.setId("fixed-id-for-tests");
        meeting.setUser(user);
        return meeting;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Meeting createUpdatedEntity() {
        Meeting meeting = new Meeting()
            .title(UPDATED_TITLE)
            .location(UPDATED_LOCATION)
            .offline_location(UPDATED_OFFLINE_LOCATION)
            .current_time(UPDATED_CURRENT_TIME)
            .from(UPDATED_FROM)
            .to(UPDATED_TO)
            .participants(UPDATED_PARTICIPANTS);
        // Add required entity
        User user = UserResourceIT.createEntity();
        user.setId("fixed-id-for-tests");
        meeting.setUser(user);
        return meeting;
    }

    @BeforeEach
    public void initTest() {
        meeting = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedMeeting != null) {
            meetingRepository.delete(insertedMeeting).block();
            insertedMeeting = null;
        }
    }

    @Test
    void createMeeting() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Meeting
        var returnedMeeting = webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(meeting))
            .exchange()
            .expectStatus()
            .isCreated()
            .expectBody(Meeting.class)
            .returnResult()
            .getResponseBody();

        // Validate the Meeting in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertMeetingUpdatableFieldsEquals(returnedMeeting, getPersistedMeeting(returnedMeeting));

        insertedMeeting = returnedMeeting;
    }

    @Test
    void createMeetingWithExistingId() throws Exception {
        // Create the Meeting with an existing ID
        meeting.setId("existing_id");

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(meeting))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Meeting in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    void checkTitleIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        meeting.setTitle(null);

        // Create the Meeting, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(meeting))
            .exchange()
            .expectStatus()
            .isBadRequest();

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkLocationIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        meeting.setLocation(null);

        // Create the Meeting, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(meeting))
            .exchange()
            .expectStatus()
            .isBadRequest();

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkOffline_locationIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        meeting.setOffline_location(null);

        // Create the Meeting, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(meeting))
            .exchange()
            .expectStatus()
            .isBadRequest();

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkCurrent_timeIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        meeting.setCurrent_time(null);

        // Create the Meeting, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(meeting))
            .exchange()
            .expectStatus()
            .isBadRequest();

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkFromIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        meeting.setFrom(null);

        // Create the Meeting, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(meeting))
            .exchange()
            .expectStatus()
            .isBadRequest();

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkToIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        meeting.setTo(null);

        // Create the Meeting, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(meeting))
            .exchange()
            .expectStatus()
            .isBadRequest();

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkParticipantsIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        meeting.setParticipants(null);

        // Create the Meeting, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(meeting))
            .exchange()
            .expectStatus()
            .isBadRequest();

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void getAllMeetings() {
        // Initialize the database
        insertedMeeting = meetingRepository.save(meeting).block();

        // Get all the meetingList
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
            .value(hasItem(meeting.getId()))
            .jsonPath("$.[*].title")
            .value(hasItem(DEFAULT_TITLE))
            .jsonPath("$.[*].location")
            .value(hasItem(DEFAULT_LOCATION.toString()))
            .jsonPath("$.[*].offline_location")
            .value(hasItem(DEFAULT_OFFLINE_LOCATION))
            .jsonPath("$.[*].current_time")
            .value(hasItem(DEFAULT_CURRENT_TIME.toString()))
            .jsonPath("$.[*].from")
            .value(hasItem(sameInstant(DEFAULT_FROM)))
            .jsonPath("$.[*].to")
            .value(hasItem(sameInstant(DEFAULT_TO)))
            .jsonPath("$.[*].participants")
            .value(hasItem(DEFAULT_PARTICIPANTS.toString()));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllMeetingsWithEagerRelationshipsIsEnabled() {
        when(meetingServiceMock.findAllWithEagerRelationships(any())).thenReturn(Flux.empty());

        webTestClient.get().uri(ENTITY_API_URL + "?eagerload=true").exchange().expectStatus().isOk();

        verify(meetingServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllMeetingsWithEagerRelationshipsIsNotEnabled() {
        when(meetingServiceMock.findAllWithEagerRelationships(any())).thenReturn(Flux.empty());

        webTestClient.get().uri(ENTITY_API_URL + "?eagerload=false").exchange().expectStatus().isOk();
        verify(meetingRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    void getMeeting() {
        // Initialize the database
        insertedMeeting = meetingRepository.save(meeting).block();

        // Get the meeting
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, meeting.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(meeting.getId()))
            .jsonPath("$.title")
            .value(is(DEFAULT_TITLE))
            .jsonPath("$.location")
            .value(is(DEFAULT_LOCATION.toString()))
            .jsonPath("$.offline_location")
            .value(is(DEFAULT_OFFLINE_LOCATION))
            .jsonPath("$.current_time")
            .value(is(DEFAULT_CURRENT_TIME.toString()))
            .jsonPath("$.from")
            .value(is(sameInstant(DEFAULT_FROM)))
            .jsonPath("$.to")
            .value(is(sameInstant(DEFAULT_TO)))
            .jsonPath("$.participants")
            .value(is(DEFAULT_PARTICIPANTS.toString()));
    }

    @Test
    void getNonExistingMeeting() {
        // Get the meeting
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_PROBLEM_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingMeeting() throws Exception {
        // Initialize the database
        insertedMeeting = meetingRepository.save(meeting).block();

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the meeting
        Meeting updatedMeeting = meetingRepository.findById(meeting.getId()).block();
        updatedMeeting
            .title(UPDATED_TITLE)
            .location(UPDATED_LOCATION)
            .offline_location(UPDATED_OFFLINE_LOCATION)
            .current_time(UPDATED_CURRENT_TIME)
            .from(UPDATED_FROM)
            .to(UPDATED_TO)
            .participants(UPDATED_PARTICIPANTS);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, updatedMeeting.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(updatedMeeting))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Meeting in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedMeetingToMatchAllProperties(updatedMeeting);
    }

    @Test
    void putNonExistingMeeting() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        meeting.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, meeting.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(meeting))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Meeting in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchMeeting() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        meeting.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, UUID.randomUUID().toString())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(meeting))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Meeting in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamMeeting() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        meeting.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(meeting))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the Meeting in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateMeetingWithPatch() throws Exception {
        // Initialize the database
        insertedMeeting = meetingRepository.save(meeting).block();

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the meeting using partial update
        Meeting partialUpdatedMeeting = new Meeting();
        partialUpdatedMeeting.setId(meeting.getId());

        partialUpdatedMeeting.offline_location(UPDATED_OFFLINE_LOCATION).to(UPDATED_TO);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedMeeting.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(om.writeValueAsBytes(partialUpdatedMeeting))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Meeting in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertMeetingUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedMeeting, meeting), getPersistedMeeting(meeting));
    }

    @Test
    void fullUpdateMeetingWithPatch() throws Exception {
        // Initialize the database
        insertedMeeting = meetingRepository.save(meeting).block();

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the meeting using partial update
        Meeting partialUpdatedMeeting = new Meeting();
        partialUpdatedMeeting.setId(meeting.getId());

        partialUpdatedMeeting
            .title(UPDATED_TITLE)
            .location(UPDATED_LOCATION)
            .offline_location(UPDATED_OFFLINE_LOCATION)
            .current_time(UPDATED_CURRENT_TIME)
            .from(UPDATED_FROM)
            .to(UPDATED_TO)
            .participants(UPDATED_PARTICIPANTS);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedMeeting.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(om.writeValueAsBytes(partialUpdatedMeeting))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Meeting in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertMeetingUpdatableFieldsEquals(partialUpdatedMeeting, getPersistedMeeting(partialUpdatedMeeting));
    }

    @Test
    void patchNonExistingMeeting() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        meeting.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, meeting.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(om.writeValueAsBytes(meeting))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Meeting in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchMeeting() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        meeting.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, UUID.randomUUID().toString())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(om.writeValueAsBytes(meeting))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Meeting in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamMeeting() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        meeting.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(om.writeValueAsBytes(meeting))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the Meeting in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteMeeting() {
        // Initialize the database
        insertedMeeting = meetingRepository.save(meeting).block();

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the meeting
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, meeting.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return meetingRepository.count().block();
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

    protected Meeting getPersistedMeeting(Meeting meeting) {
        return meetingRepository.findById(meeting.getId()).block();
    }

    protected void assertPersistedMeetingToMatchAllProperties(Meeting expectedMeeting) {
        assertMeetingAllPropertiesEquals(expectedMeeting, getPersistedMeeting(expectedMeeting));
    }

    protected void assertPersistedMeetingToMatchUpdatableProperties(Meeting expectedMeeting) {
        assertMeetingAllUpdatablePropertiesEquals(expectedMeeting, getPersistedMeeting(expectedMeeting));
    }
}

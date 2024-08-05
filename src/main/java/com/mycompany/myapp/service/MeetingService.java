package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Meeting;
import com.mycompany.myapp.repository.MeetingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link com.mycompany.myapp.domain.Meeting}.
 */
@Service
public class MeetingService {

    private static final Logger log = LoggerFactory.getLogger(MeetingService.class);

    private final MeetingRepository meetingRepository;

    public MeetingService(MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }

    /**
     * Save a meeting.
     *
     * @param meeting the entity to save.
     * @return the persisted entity.
     */
    public Mono<Meeting> save(Meeting meeting) {
        log.debug("Request to save Meeting : {}", meeting);
        return meetingRepository.save(meeting);
    }

    /**
     * Update a meeting.
     *
     * @param meeting the entity to save.
     * @return the persisted entity.
     */
    public Mono<Meeting> update(Meeting meeting) {
        log.debug("Request to update Meeting : {}", meeting);
        return meetingRepository.save(meeting);
    }

    /**
     * Partially update a meeting.
     *
     * @param meeting the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<Meeting> partialUpdate(Meeting meeting) {
        log.debug("Request to partially update Meeting : {}", meeting);

        return meetingRepository
            .findById(meeting.getId())
            .map(existingMeeting -> {
                if (meeting.getTitle() != null) {
                    existingMeeting.setTitle(meeting.getTitle());
                }
                if (meeting.getLocation() != null) {
                    existingMeeting.setLocation(meeting.getLocation());
                }
                if (meeting.getOffline_location() != null) {
                    existingMeeting.setOffline_location(meeting.getOffline_location());
                }
                if (meeting.getCurrent_time() != null) {
                    existingMeeting.setCurrent_time(meeting.getCurrent_time());
                }
                if (meeting.getFrom() != null) {
                    existingMeeting.setFrom(meeting.getFrom());
                }
                if (meeting.getTo() != null) {
                    existingMeeting.setTo(meeting.getTo());
                }
                if (meeting.getParticipants() != null) {
                    existingMeeting.setParticipants(meeting.getParticipants());
                }

                return existingMeeting;
            })
            .flatMap(meetingRepository::save);
    }

    /**
     * Get all the meetings.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Flux<Meeting> findAll(Pageable pageable) {
        log.debug("Request to get all Meetings");
        return meetingRepository.findAllBy(pageable);
    }

    /**
     * Get all the meetings with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Flux<Meeting> findAllWithEagerRelationships(Pageable pageable) {
        return meetingRepository.findAllWithEagerRelationships(pageable);
    }

    /**
     * Returns the number of meetings available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return meetingRepository.count();
    }

    /**
     * Get one meeting by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Mono<Meeting> findOne(String id) {
        log.debug("Request to get Meeting : {}", id);
        return meetingRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the meeting by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(String id) {
        log.debug("Request to delete Meeting : {}", id);
        return meetingRepository.deleteById(id);
    }
}

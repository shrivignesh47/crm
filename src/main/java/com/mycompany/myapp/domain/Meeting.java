package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mycompany.myapp.domain.enumeration.location;
import com.mycompany.myapp.domain.enumeration.participants;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import java.time.ZonedDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Meeting.
 */
@Document(collection = "meeting")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Meeting implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull(message = "must not be null")
    @Field("title")
    private String title;

    @NotNull(message = "must not be null")
    @Field("location")
    private location location;

    @NotNull(message = "must not be null")
    @Field("offline_location")
    private String offline_location;

    @NotNull(message = "must not be null")
    @Field("current_time")
    private Instant current_time;

    @NotNull(message = "must not be null")
    @Field("from")
    private ZonedDateTime from;

    @NotNull(message = "must not be null")
    @Field("to")
    private ZonedDateTime to;

    @NotNull(message = "must not be null")
    @Field("participants")
    private participants participants;

    @Field("user")
    private User user;

    @Field("contacts")
    @JsonIgnoreProperties(value = { "meetings", "deals" }, allowSetters = true)
    private Contacts contacts;

    @Field("lead")
    @JsonIgnoreProperties(value = { "user", "meetings" }, allowSetters = true)
    private Lead lead;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Meeting id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public Meeting title(String title) {
        this.setTitle(title);
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public location getLocation() {
        return this.location;
    }

    public Meeting location(location location) {
        this.setLocation(location);
        return this;
    }

    public void setLocation(location location) {
        this.location = location;
    }

    public String getOffline_location() {
        return this.offline_location;
    }

    public Meeting offline_location(String offline_location) {
        this.setOffline_location(offline_location);
        return this;
    }

    public void setOffline_location(String offline_location) {
        this.offline_location = offline_location;
    }

    public Instant getCurrent_time() {
        return this.current_time;
    }

    public Meeting current_time(Instant current_time) {
        this.setCurrent_time(current_time);
        return this;
    }

    public void setCurrent_time(Instant current_time) {
        this.current_time = current_time;
    }

    public ZonedDateTime getFrom() {
        return this.from;
    }

    public Meeting from(ZonedDateTime from) {
        this.setFrom(from);
        return this;
    }

    public void setFrom(ZonedDateTime from) {
        this.from = from;
    }

    public ZonedDateTime getTo() {
        return this.to;
    }

    public Meeting to(ZonedDateTime to) {
        this.setTo(to);
        return this;
    }

    public void setTo(ZonedDateTime to) {
        this.to = to;
    }

    public participants getParticipants() {
        return this.participants;
    }

    public Meeting participants(participants participants) {
        this.setParticipants(participants);
        return this;
    }

    public void setParticipants(participants participants) {
        this.participants = participants;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Meeting user(User user) {
        this.setUser(user);
        return this;
    }

    public Contacts getContacts() {
        return this.contacts;
    }

    public void setContacts(Contacts contacts) {
        this.contacts = contacts;
    }

    public Meeting contacts(Contacts contacts) {
        this.setContacts(contacts);
        return this;
    }

    public Lead getLead() {
        return this.lead;
    }

    public void setLead(Lead lead) {
        this.lead = lead;
    }

    public Meeting lead(Lead lead) {
        this.setLead(lead);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Meeting)) {
            return false;
        }
        return getId() != null && getId().equals(((Meeting) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Meeting{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", location='" + getLocation() + "'" +
            ", offline_location='" + getOffline_location() + "'" +
            ", current_time='" + getCurrent_time() + "'" +
            ", from='" + getFrom() + "'" +
            ", to='" + getTo() + "'" +
            ", participants='" + getParticipants() + "'" +
            "}";
    }
}

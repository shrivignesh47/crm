package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mycompany.myapp.domain.enumeration.stage;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Deal.
 */
@Document(collection = "deal")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Deal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull(message = "must not be null")
    @Field("deal_name")
    private String deal_name;

    @NotNull(message = "must not be null")
    @Field("amount")
    private Double amount;

    @NotNull(message = "must not be null")
    @Field("closing_date")
    private ZonedDateTime closing_date;

    @Field("probability")
    private Integer probability;

    @Field("next_step")
    private String next_Step;

    @Field("expected_revenue")
    private Float expected_revenue;

    @Field("campaign_source")
    private String campaign_source;

    @Field("description_information")
    private String description_information;

    @NotNull(message = "must not be null")
    @Field("stage")
    private stage stage;

    @Field("contacts")
    @JsonIgnoreProperties(value = { "meetings", "deals" }, allowSetters = true)
    private Contacts contacts;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Deal id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeal_name() {
        return this.deal_name;
    }

    public Deal deal_name(String deal_name) {
        this.setDeal_name(deal_name);
        return this;
    }

    public void setDeal_name(String deal_name) {
        this.deal_name = deal_name;
    }

    public Double getAmount() {
        return this.amount;
    }

    public Deal amount(Double amount) {
        this.setAmount(amount);
        return this;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public ZonedDateTime getClosing_date() {
        return this.closing_date;
    }

    public Deal closing_date(ZonedDateTime closing_date) {
        this.setClosing_date(closing_date);
        return this;
    }

    public void setClosing_date(ZonedDateTime closing_date) {
        this.closing_date = closing_date;
    }

    public Integer getProbability() {
        return this.probability;
    }

    public Deal probability(Integer probability) {
        this.setProbability(probability);
        return this;
    }

    public void setProbability(Integer probability) {
        this.probability = probability;
    }

    public String getNext_Step() {
        return this.next_Step;
    }

    public Deal next_Step(String next_Step) {
        this.setNext_Step(next_Step);
        return this;
    }

    public void setNext_Step(String next_Step) {
        this.next_Step = next_Step;
    }

    public Float getExpected_revenue() {
        return this.expected_revenue;
    }

    public Deal expected_revenue(Float expected_revenue) {
        this.setExpected_revenue(expected_revenue);
        return this;
    }

    public void setExpected_revenue(Float expected_revenue) {
        this.expected_revenue = expected_revenue;
    }

    public String getCampaign_source() {
        return this.campaign_source;
    }

    public Deal campaign_source(String campaign_source) {
        this.setCampaign_source(campaign_source);
        return this;
    }

    public void setCampaign_source(String campaign_source) {
        this.campaign_source = campaign_source;
    }

    public String getDescription_information() {
        return this.description_information;
    }

    public Deal description_information(String description_information) {
        this.setDescription_information(description_information);
        return this;
    }

    public void setDescription_information(String description_information) {
        this.description_information = description_information;
    }

    public stage getStage() {
        return this.stage;
    }

    public Deal stage(stage stage) {
        this.setStage(stage);
        return this;
    }

    public void setStage(stage stage) {
        this.stage = stage;
    }

    public Contacts getContacts() {
        return this.contacts;
    }

    public void setContacts(Contacts contacts) {
        this.contacts = contacts;
    }

    public Deal contacts(Contacts contacts) {
        this.setContacts(contacts);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Deal)) {
            return false;
        }
        return getId() != null && getId().equals(((Deal) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Deal{" +
            "id=" + getId() +
            ", deal_name='" + getDeal_name() + "'" +
            ", amount=" + getAmount() +
            ", closing_date='" + getClosing_date() + "'" +
            ", probability=" + getProbability() +
            ", next_Step='" + getNext_Step() + "'" +
            ", expected_revenue=" + getExpected_revenue() +
            ", campaign_source='" + getCampaign_source() + "'" +
            ", description_information='" + getDescription_information() + "'" +
            ", stage='" + getStage() + "'" +
            "}";
    }
}

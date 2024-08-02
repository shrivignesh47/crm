package com.mycompany.myapp.domain;

import com.mycompany.myapp.domain.enumeration.ownership;
import com.mycompany.myapp.domain.enumeration.rating;
import com.mycompany.myapp.domain.enumeration.type;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Accounts.
 */
@Document(collection = "accounts")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Accounts implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull(message = "must not be null")
    @Field("account_owner")
    private String account_owner;

    @NotNull(message = "must not be null")
    @Field("rating")
    private rating rating;

    @NotNull(message = "must not be null")
    @Field("phone")
    private Long phone;

    @NotNull(message = "must not be null")
    @Field("account_site")
    private String account_site;

    @Field("fax")
    private String fax;

    @NotNull(message = "must not be null")
    @Field("website")
    private String website;

    @NotNull(message = "must not be null")
    @Field("account_number")
    private Long account_number;

    @Field("ticket_symbol")
    private String ticket_Symbol;

    @NotNull(message = "must not be null")
    @Field("account_type")
    private type account_type;

    @NotNull(message = "must not be null")
    @Field("ownership")
    private ownership ownership;

    @NotNull(message = "must not be null")
    @Field("billing_street")
    private String billing_street;

    @NotNull(message = "must not be null")
    @Field("billing_city")
    private String billing_city;

    @NotNull(message = "must not be null")
    @Field("billing_state")
    private String billing_state;

    @NotNull(message = "must not be null")
    @Field("billing_code")
    private String billing_code;

    @NotNull(message = "must not be null")
    @Field("billing_country")
    private String billing_country;

    @NotNull(message = "must not be null")
    @Field("shipping_street")
    private String shipping_street;

    @Field("shipping_city")
    private String shipping_city;

    @Field("shipping_state")
    private String shipping_state;

    @Field("shipping_code")
    private String shipping_code;

    @Field("shipping_country")
    private String shipping_country;

    @NotNull(message = "must not be null")
    @Field("description")
    private String description;

    @NotNull(message = "must not be null")
    @Field("employees")
    private Integer employees;

    @Field("sic_code")
    private Long sic_code;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Accounts id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount_owner() {
        return this.account_owner;
    }

    public Accounts account_owner(String account_owner) {
        this.setAccount_owner(account_owner);
        return this;
    }

    public void setAccount_owner(String account_owner) {
        this.account_owner = account_owner;
    }

    public rating getRating() {
        return this.rating;
    }

    public Accounts rating(rating rating) {
        this.setRating(rating);
        return this;
    }

    public void setRating(rating rating) {
        this.rating = rating;
    }

    public Long getPhone() {
        return this.phone;
    }

    public Accounts phone(Long phone) {
        this.setPhone(phone);
        return this;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getAccount_site() {
        return this.account_site;
    }

    public Accounts account_site(String account_site) {
        this.setAccount_site(account_site);
        return this;
    }

    public void setAccount_site(String account_site) {
        this.account_site = account_site;
    }

    public String getFax() {
        return this.fax;
    }

    public Accounts fax(String fax) {
        this.setFax(fax);
        return this;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getWebsite() {
        return this.website;
    }

    public Accounts website(String website) {
        this.setWebsite(website);
        return this;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Long getAccount_number() {
        return this.account_number;
    }

    public Accounts account_number(Long account_number) {
        this.setAccount_number(account_number);
        return this;
    }

    public void setAccount_number(Long account_number) {
        this.account_number = account_number;
    }

    public String getTicket_Symbol() {
        return this.ticket_Symbol;
    }

    public Accounts ticket_Symbol(String ticket_Symbol) {
        this.setTicket_Symbol(ticket_Symbol);
        return this;
    }

    public void setTicket_Symbol(String ticket_Symbol) {
        this.ticket_Symbol = ticket_Symbol;
    }

    public type getAccount_type() {
        return this.account_type;
    }

    public Accounts account_type(type account_type) {
        this.setAccount_type(account_type);
        return this;
    }

    public void setAccount_type(type account_type) {
        this.account_type = account_type;
    }

    public ownership getOwnership() {
        return this.ownership;
    }

    public Accounts ownership(ownership ownership) {
        this.setOwnership(ownership);
        return this;
    }

    public void setOwnership(ownership ownership) {
        this.ownership = ownership;
    }

    public String getBilling_street() {
        return this.billing_street;
    }

    public Accounts billing_street(String billing_street) {
        this.setBilling_street(billing_street);
        return this;
    }

    public void setBilling_street(String billing_street) {
        this.billing_street = billing_street;
    }

    public String getBilling_city() {
        return this.billing_city;
    }

    public Accounts billing_city(String billing_city) {
        this.setBilling_city(billing_city);
        return this;
    }

    public void setBilling_city(String billing_city) {
        this.billing_city = billing_city;
    }

    public String getBilling_state() {
        return this.billing_state;
    }

    public Accounts billing_state(String billing_state) {
        this.setBilling_state(billing_state);
        return this;
    }

    public void setBilling_state(String billing_state) {
        this.billing_state = billing_state;
    }

    public String getBilling_code() {
        return this.billing_code;
    }

    public Accounts billing_code(String billing_code) {
        this.setBilling_code(billing_code);
        return this;
    }

    public void setBilling_code(String billing_code) {
        this.billing_code = billing_code;
    }

    public String getBilling_country() {
        return this.billing_country;
    }

    public Accounts billing_country(String billing_country) {
        this.setBilling_country(billing_country);
        return this;
    }

    public void setBilling_country(String billing_country) {
        this.billing_country = billing_country;
    }

    public String getShipping_street() {
        return this.shipping_street;
    }

    public Accounts shipping_street(String shipping_street) {
        this.setShipping_street(shipping_street);
        return this;
    }

    public void setShipping_street(String shipping_street) {
        this.shipping_street = shipping_street;
    }

    public String getShipping_city() {
        return this.shipping_city;
    }

    public Accounts shipping_city(String shipping_city) {
        this.setShipping_city(shipping_city);
        return this;
    }

    public void setShipping_city(String shipping_city) {
        this.shipping_city = shipping_city;
    }

    public String getShipping_state() {
        return this.shipping_state;
    }

    public Accounts shipping_state(String shipping_state) {
        this.setShipping_state(shipping_state);
        return this;
    }

    public void setShipping_state(String shipping_state) {
        this.shipping_state = shipping_state;
    }

    public String getShipping_code() {
        return this.shipping_code;
    }

    public Accounts shipping_code(String shipping_code) {
        this.setShipping_code(shipping_code);
        return this;
    }

    public void setShipping_code(String shipping_code) {
        this.shipping_code = shipping_code;
    }

    public String getShipping_country() {
        return this.shipping_country;
    }

    public Accounts shipping_country(String shipping_country) {
        this.setShipping_country(shipping_country);
        return this;
    }

    public void setShipping_country(String shipping_country) {
        this.shipping_country = shipping_country;
    }

    public String getDescription() {
        return this.description;
    }

    public Accounts description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getEmployees() {
        return this.employees;
    }

    public Accounts employees(Integer employees) {
        this.setEmployees(employees);
        return this;
    }

    public void setEmployees(Integer employees) {
        this.employees = employees;
    }

    public Long getSic_code() {
        return this.sic_code;
    }

    public Accounts sic_code(Long sic_code) {
        this.setSic_code(sic_code);
        return this;
    }

    public void setSic_code(Long sic_code) {
        this.sic_code = sic_code;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Accounts)) {
            return false;
        }
        return getId() != null && getId().equals(((Accounts) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Accounts{" +
            "id=" + getId() +
            ", account_owner='" + getAccount_owner() + "'" +
            ", rating='" + getRating() + "'" +
            ", phone=" + getPhone() +
            ", account_site='" + getAccount_site() + "'" +
            ", fax='" + getFax() + "'" +
            ", website='" + getWebsite() + "'" +
            ", account_number=" + getAccount_number() +
            ", ticket_Symbol='" + getTicket_Symbol() + "'" +
            ", account_type='" + getAccount_type() + "'" +
            ", ownership='" + getOwnership() + "'" +
            ", billing_street='" + getBilling_street() + "'" +
            ", billing_city='" + getBilling_city() + "'" +
            ", billing_state='" + getBilling_state() + "'" +
            ", billing_code='" + getBilling_code() + "'" +
            ", billing_country='" + getBilling_country() + "'" +
            ", shipping_street='" + getShipping_street() + "'" +
            ", shipping_city='" + getShipping_city() + "'" +
            ", shipping_state='" + getShipping_state() + "'" +
            ", shipping_code='" + getShipping_code() + "'" +
            ", shipping_country='" + getShipping_country() + "'" +
            ", description='" + getDescription() + "'" +
            ", employees=" + getEmployees() +
            ", sic_code=" + getSic_code() +
            "}";
    }
}

package com.mycompany.myapp.domain;

import com.mycompany.myapp.domain.enumeration.source;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Contacts.
 */
@Document(collection = "contacts")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Contacts implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull(message = "must not be null")
    @Field("first_name")
    private String first_name;

    @NotNull(message = "must not be null")
    @Field("last_name")
    private String last_name;

    @NotNull(message = "must not be null")
    @Field("account_name")
    private String account_name;

    @NotNull(message = "must not be null")
    @Field("vendor_name")
    private String vendor_name;

    @NotNull(message = "must not be null")
    @Field("lead_source")
    private source lead_source;

    @NotNull(message = "must not be null")
    @Field("email")
    private String email;

    @NotNull(message = "must not be null")
    @Field("title")
    private String title;

    @NotNull(message = "must not be null")
    @Field("phone")
    private Long phone;

    @NotNull(message = "must not be null")
    @Field("department")
    private String department;

    @NotNull(message = "must not be null")
    @Field("mobile")
    private Long mobile;

    @Field("fax")
    private String fax;

    @NotNull(message = "must not be null")
    @Field("date_of_birth")
    private LocalDate date_of_birth;

    @NotNull(message = "must not be null")
    @Field("social_media_handle")
    private String social_media_handle;

    @NotNull(message = "must not be null")
    @Field("street")
    private String street;

    @NotNull(message = "must not be null")
    @Field("city")
    private String city;

    @NotNull(message = "must not be null")
    @Field("state")
    private String state;

    @NotNull(message = "must not be null")
    @Field("zip")
    private Long zip;

    @NotNull(message = "must not be null")
    @Field("country")
    private String country;

    @NotNull(message = "must not be null")
    @Field("description")
    private String description;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Contacts id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirst_name() {
        return this.first_name;
    }

    public Contacts first_name(String first_name) {
        this.setFirst_name(first_name);
        return this;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return this.last_name;
    }

    public Contacts last_name(String last_name) {
        this.setLast_name(last_name);
        return this;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getAccount_name() {
        return this.account_name;
    }

    public Contacts account_name(String account_name) {
        this.setAccount_name(account_name);
        return this;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public String getVendor_name() {
        return this.vendor_name;
    }

    public Contacts vendor_name(String vendor_name) {
        this.setVendor_name(vendor_name);
        return this;
    }

    public void setVendor_name(String vendor_name) {
        this.vendor_name = vendor_name;
    }

    public source getLead_source() {
        return this.lead_source;
    }

    public Contacts lead_source(source lead_source) {
        this.setLead_source(lead_source);
        return this;
    }

    public void setLead_source(source lead_source) {
        this.lead_source = lead_source;
    }

    public String getEmail() {
        return this.email;
    }

    public Contacts email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return this.title;
    }

    public Contacts title(String title) {
        this.setTitle(title);
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getPhone() {
        return this.phone;
    }

    public Contacts phone(Long phone) {
        this.setPhone(phone);
        return this;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getDepartment() {
        return this.department;
    }

    public Contacts department(String department) {
        this.setDepartment(department);
        return this;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Long getMobile() {
        return this.mobile;
    }

    public Contacts mobile(Long mobile) {
        this.setMobile(mobile);
        return this;
    }

    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }

    public String getFax() {
        return this.fax;
    }

    public Contacts fax(String fax) {
        this.setFax(fax);
        return this;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public LocalDate getDate_of_birth() {
        return this.date_of_birth;
    }

    public Contacts date_of_birth(LocalDate date_of_birth) {
        this.setDate_of_birth(date_of_birth);
        return this;
    }

    public void setDate_of_birth(LocalDate date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getSocial_media_handle() {
        return this.social_media_handle;
    }

    public Contacts social_media_handle(String social_media_handle) {
        this.setSocial_media_handle(social_media_handle);
        return this;
    }

    public void setSocial_media_handle(String social_media_handle) {
        this.social_media_handle = social_media_handle;
    }

    public String getStreet() {
        return this.street;
    }

    public Contacts street(String street) {
        this.setStreet(street);
        return this;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return this.city;
    }

    public Contacts city(String city) {
        this.setCity(city);
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return this.state;
    }

    public Contacts state(String state) {
        this.setState(state);
        return this;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getZip() {
        return this.zip;
    }

    public Contacts zip(Long zip) {
        this.setZip(zip);
        return this;
    }

    public void setZip(Long zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return this.country;
    }

    public Contacts country(String country) {
        this.setCountry(country);
        return this;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescription() {
        return this.description;
    }

    public Contacts description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Contacts)) {
            return false;
        }
        return getId() != null && getId().equals(((Contacts) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Contacts{" +
            "id=" + getId() +
            ", first_name='" + getFirst_name() + "'" +
            ", last_name='" + getLast_name() + "'" +
            ", account_name='" + getAccount_name() + "'" +
            ", vendor_name='" + getVendor_name() + "'" +
            ", lead_source='" + getLead_source() + "'" +
            ", email='" + getEmail() + "'" +
            ", title='" + getTitle() + "'" +
            ", phone=" + getPhone() +
            ", department='" + getDepartment() + "'" +
            ", mobile=" + getMobile() +
            ", fax='" + getFax() + "'" +
            ", date_of_birth='" + getDate_of_birth() + "'" +
            ", social_media_handle='" + getSocial_media_handle() + "'" +
            ", street='" + getStreet() + "'" +
            ", city='" + getCity() + "'" +
            ", state='" + getState() + "'" +
            ", zip=" + getZip() +
            ", country='" + getCountry() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}

package com.mycompany.myapp.domain;

import com.mycompany.myapp.domain.enumeration.industry;
import com.mycompany.myapp.domain.enumeration.rating;
import com.mycompany.myapp.domain.enumeration.social;
import com.mycompany.myapp.domain.enumeration.source;
import com.mycompany.myapp.domain.enumeration.status;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Lead.
 */
@Document(collection = "lead")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Lead implements Serializable {

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
    @Field("company")
    private String company;

    @NotNull(message = "must not be null")
    @Field("title")
    private String title;

    @NotNull(message = "must not be null")
    @Field("email")
    private String email;

    @Field("fax")
    private String fax;

    @NotNull(message = "must not be null")
    @Field("website")
    private String website;

    @NotNull(message = "must not be null")
    @Field("lead_source")
    private source lead_source;

    @NotNull(message = "must not be null")
    @Field("lead_status")
    private status lead_status;

    @NotNull(message = "must not be null")
    @Field("industry")
    private industry industry;

    @NotNull(message = "must not be null")
    @Field("no_of_emp")
    private Integer no_of_emp;

    @Field("annual_revenue")
    private Float annual_revenue;

    @NotNull(message = "must not be null")
    @Field("rating")
    private rating rating;

    @Field("social_media")
    private social social_media;

    @Field("media_handle_id")
    private String media_handle_id;

    @NotNull(message = "must not be null")
    @Field("street")
    private String street;

    @NotNull(message = "must not be null")
    @Size(min = 0, max = 20)
    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    @Field("city")
    private String city;

    @NotNull(message = "must not be null")
    @Field("state")
    private String state;

    @NotNull(message = "must not be null")
    @Field("zipcode")
    private Integer zipcode;

    @NotNull(message = "must not be null")
    @Field("country")
    private String country;

    @Field("description")
    private String description;

    @Field("lead_image")
    private byte[] lead_image;

    @Field("lead_image_content_type")
    private String lead_imageContentType;

    @NotNull(message = "must not be null")
    @Field("phone")
    private Integer phone;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Lead id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirst_name() {
        return this.first_name;
    }

    public Lead first_name(String first_name) {
        this.setFirst_name(first_name);
        return this;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return this.last_name;
    }

    public Lead last_name(String last_name) {
        this.setLast_name(last_name);
        return this;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getCompany() {
        return this.company;
    }

    public Lead company(String company) {
        this.setCompany(company);
        return this;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTitle() {
        return this.title;
    }

    public Lead title(String title) {
        this.setTitle(title);
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmail() {
        return this.email;
    }

    public Lead email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFax() {
        return this.fax;
    }

    public Lead fax(String fax) {
        this.setFax(fax);
        return this;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getWebsite() {
        return this.website;
    }

    public Lead website(String website) {
        this.setWebsite(website);
        return this;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public source getLead_source() {
        return this.lead_source;
    }

    public Lead lead_source(source lead_source) {
        this.setLead_source(lead_source);
        return this;
    }

    public void setLead_source(source lead_source) {
        this.lead_source = lead_source;
    }

    public status getLead_status() {
        return this.lead_status;
    }

    public Lead lead_status(status lead_status) {
        this.setLead_status(lead_status);
        return this;
    }

    public void setLead_status(status lead_status) {
        this.lead_status = lead_status;
    }

    public industry getIndustry() {
        return this.industry;
    }

    public Lead industry(industry industry) {
        this.setIndustry(industry);
        return this;
    }

    public void setIndustry(industry industry) {
        this.industry = industry;
    }

    public Integer getNo_of_emp() {
        return this.no_of_emp;
    }

    public Lead no_of_emp(Integer no_of_emp) {
        this.setNo_of_emp(no_of_emp);
        return this;
    }

    public void setNo_of_emp(Integer no_of_emp) {
        this.no_of_emp = no_of_emp;
    }

    public Float getAnnual_revenue() {
        return this.annual_revenue;
    }

    public Lead annual_revenue(Float annual_revenue) {
        this.setAnnual_revenue(annual_revenue);
        return this;
    }

    public void setAnnual_revenue(Float annual_revenue) {
        this.annual_revenue = annual_revenue;
    }

    public rating getRating() {
        return this.rating;
    }

    public Lead rating(rating rating) {
        this.setRating(rating);
        return this;
    }

    public void setRating(rating rating) {
        this.rating = rating;
    }

    public social getSocial_media() {
        return this.social_media;
    }

    public Lead social_media(social social_media) {
        this.setSocial_media(social_media);
        return this;
    }

    public void setSocial_media(social social_media) {
        this.social_media = social_media;
    }

    public String getMedia_handle_id() {
        return this.media_handle_id;
    }

    public Lead media_handle_id(String media_handle_id) {
        this.setMedia_handle_id(media_handle_id);
        return this;
    }

    public void setMedia_handle_id(String media_handle_id) {
        this.media_handle_id = media_handle_id;
    }

    public String getStreet() {
        return this.street;
    }

    public Lead street(String street) {
        this.setStreet(street);
        return this;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return this.city;
    }

    public Lead city(String city) {
        this.setCity(city);
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return this.state;
    }

    public Lead state(String state) {
        this.setState(state);
        return this;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getZipcode() {
        return this.zipcode;
    }

    public Lead zipcode(Integer zipcode) {
        this.setZipcode(zipcode);
        return this;
    }

    public void setZipcode(Integer zipcode) {
        this.zipcode = zipcode;
    }

    public String getCountry() {
        return this.country;
    }

    public Lead country(String country) {
        this.setCountry(country);
        return this;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescription() {
        return this.description;
    }

    public Lead description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getLead_image() {
        return this.lead_image;
    }

    public Lead lead_image(byte[] lead_image) {
        this.setLead_image(lead_image);
        return this;
    }

    public void setLead_image(byte[] lead_image) {
        this.lead_image = lead_image;
    }

    public String getLead_imageContentType() {
        return this.lead_imageContentType;
    }

    public Lead lead_imageContentType(String lead_imageContentType) {
        this.lead_imageContentType = lead_imageContentType;
        return this;
    }

    public void setLead_imageContentType(String lead_imageContentType) {
        this.lead_imageContentType = lead_imageContentType;
    }

    public Integer getPhone() {
        return this.phone;
    }

    public Lead phone(Integer phone) {
        this.setPhone(phone);
        return this;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Lead)) {
            return false;
        }
        return getId() != null && getId().equals(((Lead) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Lead{" +
            "id=" + getId() +
            ", first_name='" + getFirst_name() + "'" +
            ", last_name='" + getLast_name() + "'" +
            ", company='" + getCompany() + "'" +
            ", title='" + getTitle() + "'" +
            ", email='" + getEmail() + "'" +
            ", fax='" + getFax() + "'" +
            ", website='" + getWebsite() + "'" +
            ", lead_source='" + getLead_source() + "'" +
            ", lead_status='" + getLead_status() + "'" +
            ", industry='" + getIndustry() + "'" +
            ", no_of_emp=" + getNo_of_emp() +
            ", annual_revenue=" + getAnnual_revenue() +
            ", rating='" + getRating() + "'" +
            ", social_media='" + getSocial_media() + "'" +
            ", media_handle_id='" + getMedia_handle_id() + "'" +
            ", street='" + getStreet() + "'" +
            ", city='" + getCity() + "'" +
            ", state='" + getState() + "'" +
            ", zipcode=" + getZipcode() +
            ", country='" + getCountry() + "'" +
            ", description='" + getDescription() + "'" +
            ", lead_image='" + getLead_image() + "'" +
            ", lead_imageContentType='" + getLead_imageContentType() + "'" +
            ", phone=" + getPhone() +
            "}";
    }
}

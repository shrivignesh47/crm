package com.mycompany.myapp.domain;

import static com.mycompany.myapp.domain.ContactsTestSamples.*;
import static com.mycompany.myapp.domain.DealTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DealTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Deal.class);
        Deal deal1 = getDealSample1();
        Deal deal2 = new Deal();
        assertThat(deal1).isNotEqualTo(deal2);

        deal2.setId(deal1.getId());
        assertThat(deal1).isEqualTo(deal2);

        deal2 = getDealSample2();
        assertThat(deal1).isNotEqualTo(deal2);
    }

    @Test
    void contactsTest() {
        Deal deal = getDealRandomSampleGenerator();
        Contacts contactsBack = getContactsRandomSampleGenerator();

        deal.setContacts(contactsBack);
        assertThat(deal.getContacts()).isEqualTo(contactsBack);

        deal.contacts(null);
        assertThat(deal.getContacts()).isNull();
    }
}

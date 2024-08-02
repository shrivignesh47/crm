package com.mycompany.myapp.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class ContactsTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Contacts getContactsSample1() {
        return new Contacts()
            .id("id1")
            .first_name("first_name1")
            .last_name("last_name1")
            .account_name("account_name1")
            .vendor_name("vendor_name1")
            .email("email1")
            .title("title1")
            .phone(1L)
            .department("department1")
            .mobile(1L)
            .fax("fax1")
            .social_media_handle("social_media_handle1")
            .street("street1")
            .city("city1")
            .state("state1")
            .zip(1L)
            .country("country1")
            .description("description1");
    }

    public static Contacts getContactsSample2() {
        return new Contacts()
            .id("id2")
            .first_name("first_name2")
            .last_name("last_name2")
            .account_name("account_name2")
            .vendor_name("vendor_name2")
            .email("email2")
            .title("title2")
            .phone(2L)
            .department("department2")
            .mobile(2L)
            .fax("fax2")
            .social_media_handle("social_media_handle2")
            .street("street2")
            .city("city2")
            .state("state2")
            .zip(2L)
            .country("country2")
            .description("description2");
    }

    public static Contacts getContactsRandomSampleGenerator() {
        return new Contacts()
            .id(UUID.randomUUID().toString())
            .first_name(UUID.randomUUID().toString())
            .last_name(UUID.randomUUID().toString())
            .account_name(UUID.randomUUID().toString())
            .vendor_name(UUID.randomUUID().toString())
            .email(UUID.randomUUID().toString())
            .title(UUID.randomUUID().toString())
            .phone(longCount.incrementAndGet())
            .department(UUID.randomUUID().toString())
            .mobile(longCount.incrementAndGet())
            .fax(UUID.randomUUID().toString())
            .social_media_handle(UUID.randomUUID().toString())
            .street(UUID.randomUUID().toString())
            .city(UUID.randomUUID().toString())
            .state(UUID.randomUUID().toString())
            .zip(longCount.incrementAndGet())
            .country(UUID.randomUUID().toString())
            .description(UUID.randomUUID().toString());
    }
}

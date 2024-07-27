package com.mycompany.myapp.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class LeadTestSamples {

    private static final Random random = new Random();
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Lead getLeadSample1() {
        return new Lead()
            .id("id1")
            .first_name("first_name1")
            .last_name("last_name1")
            .company("company1")
            .title("title1")
            .email("email1")
            .fax("fax1")
            .website("website1")
            .no_of_emp(1)
            .media_handle_id("media_handle_id1")
            .street("street1")
            .city("city1")
            .state("state1")
            .zipcode(1)
            .country("country1")
            .description("description1")
            .phone(1);
    }

    public static Lead getLeadSample2() {
        return new Lead()
            .id("id2")
            .first_name("first_name2")
            .last_name("last_name2")
            .company("company2")
            .title("title2")
            .email("email2")
            .fax("fax2")
            .website("website2")
            .no_of_emp(2)
            .media_handle_id("media_handle_id2")
            .street("street2")
            .city("city2")
            .state("state2")
            .zipcode(2)
            .country("country2")
            .description("description2")
            .phone(2);
    }

    public static Lead getLeadRandomSampleGenerator() {
        return new Lead()
            .id(UUID.randomUUID().toString())
            .first_name(UUID.randomUUID().toString())
            .last_name(UUID.randomUUID().toString())
            .company(UUID.randomUUID().toString())
            .title(UUID.randomUUID().toString())
            .email(UUID.randomUUID().toString())
            .fax(UUID.randomUUID().toString())
            .website(UUID.randomUUID().toString())
            .no_of_emp(intCount.incrementAndGet())
            .media_handle_id(UUID.randomUUID().toString())
            .street(UUID.randomUUID().toString())
            .city(UUID.randomUUID().toString())
            .state(UUID.randomUUID().toString())
            .zipcode(intCount.incrementAndGet())
            .country(UUID.randomUUID().toString())
            .description(UUID.randomUUID().toString())
            .phone(intCount.incrementAndGet());
    }
}

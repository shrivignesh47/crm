package com.mycompany.myapp.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class AccountsTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Accounts getAccountsSample1() {
        return new Accounts()
            .id("id1")
            .account_owner("account_owner1")
            .phone(1L)
            .account_site("account_site1")
            .fax("fax1")
            .website("website1")
            .account_number(1L)
            .ticket_Symbol("ticket_Symbol1")
            .billing_street("billing_street1")
            .billing_city("billing_city1")
            .billing_state("billing_state1")
            .billing_code("billing_code1")
            .billing_country("billing_country1")
            .shipping_street("shipping_street1")
            .shipping_city("shipping_city1")
            .shipping_state("shipping_state1")
            .shipping_code("shipping_code1")
            .shipping_country("shipping_country1")
            .description("description1")
            .employees(1)
            .sic_code(1L);
    }

    public static Accounts getAccountsSample2() {
        return new Accounts()
            .id("id2")
            .account_owner("account_owner2")
            .phone(2L)
            .account_site("account_site2")
            .fax("fax2")
            .website("website2")
            .account_number(2L)
            .ticket_Symbol("ticket_Symbol2")
            .billing_street("billing_street2")
            .billing_city("billing_city2")
            .billing_state("billing_state2")
            .billing_code("billing_code2")
            .billing_country("billing_country2")
            .shipping_street("shipping_street2")
            .shipping_city("shipping_city2")
            .shipping_state("shipping_state2")
            .shipping_code("shipping_code2")
            .shipping_country("shipping_country2")
            .description("description2")
            .employees(2)
            .sic_code(2L);
    }

    public static Accounts getAccountsRandomSampleGenerator() {
        return new Accounts()
            .id(UUID.randomUUID().toString())
            .account_owner(UUID.randomUUID().toString())
            .phone(longCount.incrementAndGet())
            .account_site(UUID.randomUUID().toString())
            .fax(UUID.randomUUID().toString())
            .website(UUID.randomUUID().toString())
            .account_number(longCount.incrementAndGet())
            .ticket_Symbol(UUID.randomUUID().toString())
            .billing_street(UUID.randomUUID().toString())
            .billing_city(UUID.randomUUID().toString())
            .billing_state(UUID.randomUUID().toString())
            .billing_code(UUID.randomUUID().toString())
            .billing_country(UUID.randomUUID().toString())
            .shipping_street(UUID.randomUUID().toString())
            .shipping_city(UUID.randomUUID().toString())
            .shipping_state(UUID.randomUUID().toString())
            .shipping_code(UUID.randomUUID().toString())
            .shipping_country(UUID.randomUUID().toString())
            .description(UUID.randomUUID().toString())
            .employees(intCount.incrementAndGet())
            .sic_code(longCount.incrementAndGet());
    }
}

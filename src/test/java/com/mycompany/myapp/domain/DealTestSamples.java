package com.mycompany.myapp.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class DealTestSamples {

    private static final Random random = new Random();
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Deal getDealSample1() {
        return new Deal()
            .id("id1")
            .deal_name("deal_name1")
            .probability(1)
            .next_Step("next_Step1")
            .campaign_source("campaign_source1")
            .description_information("description_information1");
    }

    public static Deal getDealSample2() {
        return new Deal()
            .id("id2")
            .deal_name("deal_name2")
            .probability(2)
            .next_Step("next_Step2")
            .campaign_source("campaign_source2")
            .description_information("description_information2");
    }

    public static Deal getDealRandomSampleGenerator() {
        return new Deal()
            .id(UUID.randomUUID().toString())
            .deal_name(UUID.randomUUID().toString())
            .probability(intCount.incrementAndGet())
            .next_Step(UUID.randomUUID().toString())
            .campaign_source(UUID.randomUUID().toString())
            .description_information(UUID.randomUUID().toString());
    }
}

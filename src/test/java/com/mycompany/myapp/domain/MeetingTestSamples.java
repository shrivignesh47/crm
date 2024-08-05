package com.mycompany.myapp.domain;

import java.util.UUID;

public class MeetingTestSamples {

    public static Meeting getMeetingSample1() {
        return new Meeting().id("id1").title("title1").offline_location("offline_location1");
    }

    public static Meeting getMeetingSample2() {
        return new Meeting().id("id2").title("title2").offline_location("offline_location2");
    }

    public static Meeting getMeetingRandomSampleGenerator() {
        return new Meeting()
            .id(UUID.randomUUID().toString())
            .title(UUID.randomUUID().toString())
            .offline_location(UUID.randomUUID().toString());
    }
}

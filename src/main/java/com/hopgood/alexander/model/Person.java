package com.hopgood.alexander.model;

import java.time.ZonedDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Person {
    private ZonedDateTime dateOfBirth;
    private String fullName;
    private Gender gender;
}

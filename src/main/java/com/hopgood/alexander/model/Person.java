package com.hopgood.alexander.model;

import java.time.LocalDate;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Getter
@Builder
public class Person {
    private LocalDate dateOfBirth;
    private String fullName;
    private Gender gender;
}

package com.hopgood.alexander.model;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;

class PersonTest {

    private final LocalDate dob = LocalDate.of(1980, 7, 26);
    private final String fullName = "Jacinda Ardern";
    private final Gender gender = Gender.FEMALE;

    @Test
    void testGetDateOfBirth() {
        Person person = Person.builder()
                .dateOfBirth(dob)
                .build();
        assertThat(person.getDateOfBirth()).isEqualTo(dob);
        assertThat(person.getFullName()).isNull();
        assertThat(person.getGender()).isNull();
    }

    @Test
    void testGetFullname() {
        Person person = Person.builder()
                .fullName(fullName)
                .build();
        assertThat(person.getDateOfBirth()).isNull();
        assertThat(person.getFullName()).isEqualTo(fullName);
        assertThat(person.getGender()).isNull();
    }

    @Test
    void testGetGender() {
        Person person = Person.builder()
                .gender(gender)
                .build();
        assertThat(person.getDateOfBirth()).isNull();
        assertThat(person.getFullName()).isNull();
        assertThat(person.getGender()).isEqualTo(gender);
    }

    @Test
    void testGetFullBuilder() {
        Person person = Person.builder()
                .dateOfBirth(dob)
                .gender(gender)
                .fullName(fullName)
                .build();
        assertThat(person.getDateOfBirth()).isEqualTo(dob);
        assertThat(person.getFullName()).isEqualTo(fullName);
        assertThat(person.getGender()).isEqualTo(gender);
    }
}
package com.hopgood.alexander.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GenderTest {

    @Test
    void testValueOf_givenUnknownGender_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Gender.valueOf("unknown"));
    }

    @Test
    void testValueOf_givenLowercaseGender_male_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Gender.valueOf("male"));
    }

    @Test
    void testValueOf_givenMixedcaseGender_male_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Gender.valueOf("mAlE"));
    }

    @Test
    void testValueOf_givenUppercaseGender_male() {
        assertThat(Gender.valueOf("MALE")).isEqualTo(Gender.MALE);
    }

    @Test
    void testValueOf_givenUppercaseGender_female() {
        assertThat(Gender.valueOf("FEMALE")).isEqualTo(Gender.FEMALE);
    }

    @Test
    void testValueOf_givenLowercaseGender_female_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Gender.valueOf("female"));
    }

    @Test
    void testValueOf_givenMixedcaseGender_female_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Gender.valueOf("fEmAlE"));
    }

    @Test
    void testFromString_givenLowercaseGender_male() {
        assertThat(Gender.fromString("male")).isEqualTo(Gender.MALE);
    }

    @Test
    void testFromString_givenMixedcaseGender_male() {
        assertThat(Gender.fromString("mAlE")).isEqualTo(Gender.MALE);
    }

    @Test
    void testFromString_givenUppercaseGender_male() {
        assertThat(Gender.fromString("MALE")).isEqualTo(Gender.MALE);
    }

    @Test
    void testFromString_givenUppercaseGender_female() {
        assertThat(Gender.fromString("FEMALE")).isEqualTo(Gender.FEMALE);
    }

    @Test
    void testFromString_givenLowercaseGender_female() {
        assertThat(Gender.fromString("female")).isEqualTo(Gender.FEMALE);
    }

    @Test
    void testFromString_givenMixedcaseGender_female() {
        assertThat(Gender.fromString("fEmAlE")).isEqualTo(Gender.FEMALE);
    }
}
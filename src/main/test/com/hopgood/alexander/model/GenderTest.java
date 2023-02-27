package com.hopgood.alexander.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GenderTest {

    @Test
    void testUnknownGender_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> Gender.valueOf("unknown"));
    }

    @Test
    void testLowercaseGender_male() {
        assertThrows(IllegalArgumentException.class, () -> Gender.valueOf("male"));
    }

    @Test
    void testMixedcaseGender_male() {
        assertThrows(IllegalArgumentException.class, () -> Gender.valueOf("mAlE"));
    }

    @Test
    void testUppercaseGender_male() {
        assertThat(Gender.valueOf("MALE")).isEqualTo(Gender.MALE);
    }

    @Test
    void testUppercaseGender_female() {
        assertThat(Gender.valueOf("FEMALE")).isEqualTo(Gender.FEMALE);
    }

    @Test
    void testLowercaseGender_female() {
        assertThrows(IllegalArgumentException.class, () -> Gender.valueOf("female"));
    }

    @Test
    void testMixedcaseGender_female() {
        assertThrows(IllegalArgumentException.class, () -> Gender.valueOf("fEmAlE"));
    }
}
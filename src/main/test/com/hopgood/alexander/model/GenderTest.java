package com.hopgood.alexander.model;

import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GenderTest {

    @Test
    void testUnknownGender_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> Gender.valueOf("unknown"));
    }

    @Test
    void testLowercaseGender_male() {
        assertThat(Gender.valueOf("male")).isEqualTo(Gender.MALE);
    }

    @Test
    void testUppercaseGender_male() {
        assertThat(Gender.valueOf("MALE")).isEqualTo(Gender.MALE);
    }

    @Test
    void testMixedcaseGender_male() {
        assertThat(Gender.valueOf("mAlE")).isEqualTo(Gender.MALE);
    }

    @Test
    void testLowercaseGender_female() {
        assertThat(Gender.valueOf("female")).isEqualTo(Gender.FEMALE);
    }

    @Test
    void testUppercaseGender_female() {
        assertThat(Gender.valueOf("FEMALE")).isEqualTo(Gender.FEMALE);
    }

    @Test
    void testMixedcaseGender_female() {
        assertThat(Gender.valueOf("fEmAlE")).isEqualTo(Gender.FEMALE);
    }
}
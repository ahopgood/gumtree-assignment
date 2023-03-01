package com.hopgood.alexander;

import com.hopgood.alexander.model.Gender;
import com.hopgood.alexander.model.Person;
import com.opencsv.exceptions.CsvException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;

class ApplicationIT {

    private final Application application = new Application();

    ApplicationIT() throws IOException, URISyntaxException, CsvException {
    }

    @Test
    void testQuestion1() {
        assertThat(application.question1()).isEqualTo(3);
    }

    @Test
    void testQuestion2() {
        assertThat(application.question2()).isEqualTo(Person.builder()
                .fullName("Wes Jackson")
                .gender(Gender.MALE)
                .dateOfBirth(LocalDate.of(1974, 8, 14))
                .build());
    }

    @Test
    void testQuestion3() {
        assertThat(application.question3()).isEqualTo(2862);
    }

}
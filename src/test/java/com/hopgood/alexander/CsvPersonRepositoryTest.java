package com.hopgood.alexander;

import com.hopgood.alexander.CsvPersonRepository;
import com.hopgood.alexander.model.Gender;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.csv.Csv;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CsvPersonRepositoryTest {

    private CsvPersonRepository csvPersonRepository;

    private Path csvSourceFilePath = Path.of(getClass().getResource("../../../AddressBook").toURI());

    CsvPersonRepositoryTest() throws URISyntaxException {
    }

    @Test
    void testGivenFileNotFound() {
        assertThrows(FileNotFoundException.class, () -> new CsvPersonRepository(Paths.get("unknown.csv")));
    }

    @Test
    void test() throws IOException {
        csvPersonRepository = new CsvPersonRepository(csvSourceFilePath);
        assertThat(csvPersonRepository.getAll()).isNotNull();
        assertThat(csvPersonRepository.getAll()).isNotEmpty();
    }

    @Test
    void testLocalDateFromString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yy");
        LocalDate date = LocalDate.parse("01/10/20", dateFormatter);
        assertThat(date.getYear()).isEqualTo(2020);
        assertThat(date.getMonth()).isEqualTo(Month.OCTOBER);
        assertThat(date.getDayOfMonth()).isEqualTo(1);
    }

    @Test
    void testLowerCaseGender_toGender() throws IOException {
        csvPersonRepository = new CsvPersonRepository(csvSourceFilePath);
        assertThat(csvPersonRepository.fromString("female")).isEqualTo(Gender.FEMALE);
    }

    @Test
    void testMixedCaseGender_toGender() throws IOException {
        csvPersonRepository = new CsvPersonRepository(csvSourceFilePath);
        assertThat(csvPersonRepository.fromString("fEmAlE")).isEqualTo(Gender.FEMALE);
    }
}
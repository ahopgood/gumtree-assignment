package com.hopgood.alexander;

import com.hopgood.alexander.model.Gender;
import com.hopgood.alexander.model.Person;
import com.opencsv.exceptions.CsvException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeParseException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CsvPersonRepositoryTest {
    private Path csvSourceFilePath = Path.of(getClass().getResource("../../../AddressBook").toURI());

    private CsvPersonRepository csvPersonRepository = new CsvPersonRepository(csvSourceFilePath);;

    CsvPersonRepositoryTest() throws URISyntaxException, IOException, CsvException {
    }

    @Test
    void testGivenFileNotFound() {
        assertThrows(FileNotFoundException.class, () -> new CsvPersonRepository(Paths.get("unknown.csv")));
    }

    @Test
    void test() throws IOException, CsvException {
        csvPersonRepository = new CsvPersonRepository(csvSourceFilePath);
        assertThat(csvPersonRepository.getAll()).isNotNull();
        assertThat(csvPersonRepository.getAll()).isNotEmpty();
    }

    @Test
    void testMap_withWrongDateFormat_fourDigitYear() {
        String[] record = new String[]{"Bob Hope","Male"," 01/02/2023"};
        assertThrows(DateTimeParseException.class, () -> csvPersonRepository.map(record));
    }
    @Test
    void testMap_withWrongDateFormat_hyphensAsSeparators() {
        String[] record = new String[]{"Bob Hope","Male"," 01-02-23"};
        assertThrows(DateTimeParseException.class, () -> csvPersonRepository.map(record));
    }
    @Test
    void testMap_withLeadingWhitespace() {
        String[] record = new String[]{" Bob Hope"," Male"," 01/02/23"};
        Person person = csvPersonRepository.map(record);

        LocalDate dob = person.getDateOfBirth();
        assertThat(dob.getYear()).isEqualTo(2023);
        assertThat(dob.getMonth()).isEqualTo(Month.FEBRUARY);
        assertThat(dob.getDayOfMonth()).isEqualTo(1);

        assertThat(person.getGender()).isEqualTo(Gender.MALE);
        assertThat(person.getFullName()).isEqualTo("Bob Hope");
    }
}
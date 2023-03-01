package com.hopgood.alexander;

import com.hopgood.alexander.model.Gender;
import com.hopgood.alexander.model.Person;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.List;
public class CsvPersonRepository implements PersonRepository {

    private final DateTimeFormatter dateFormatter =
            new DateTimeFormatterBuilder().appendPattern("dd/MM/")
                    .appendValueReduced(ChronoField.YEAR, 2, 2, 1940)
                    .toFormatter();

    private final List<Person> persons;
    public CsvPersonRepository(Path csvSourceFilePath) throws IOException, CsvException {
        CSVReader reader = new CSVReaderBuilder(new FileReader(csvSourceFilePath.toFile()))
                .build();
        this.persons = reader.readAll().stream().map(this::map).toList();
    }

    Person map(String[] record) {
        return Person.builder()
                .dateOfBirth(LocalDate.parse(record[2].trim(), dateFormatter))
                .gender(Gender.fromString(record[1].trim()))
                .fullName(record[0].trim())
                .build();
    }
    @Override
    public List<Person> getAll() {
        return this.persons;
    }
}

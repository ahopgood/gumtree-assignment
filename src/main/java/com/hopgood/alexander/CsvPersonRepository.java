package com.hopgood.alexander;

import com.hopgood.alexander.model.Gender;
import com.hopgood.alexander.model.Person;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class CsvPersonRepository implements PersonRepository {

    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yy");

    private final List<Person> persons = new LinkedList<>();
    public CsvPersonRepository(Path csvSourceFilePath) throws IOException {
        Reader in = new FileReader(csvSourceFilePath.toFile());
        CSVFormat format = CSVFormat.DEFAULT.builder()
                .setIgnoreSurroundingSpaces(true) //crucial to handle whitespace around csv entries
                .build();

        Iterable<CSVRecord> records = CSVParser.parse(in ,format);
        for (CSVRecord record : records) {
            persons.add(Person.builder()
                    .dateOfBirth(LocalDate.parse(record.get(2), dateFormatter))
                    .gender(fromString(record.get(1)))
                    .fullName(record.get(0))
                    .build()
            );
        }
    }
    @Override
    public List<Person> getAll() {
        return this.persons;
    }

    Gender fromString(String gender) {
        return Gender.valueOf(gender.toUpperCase());
    }
}

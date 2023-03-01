package com.hopgood.alexander;

import com.hopgood.alexander.model.Person;
import com.opencsv.exceptions.CsvException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.Optional;

public class Application {
    private final PersonService personService;

    public Application() throws IOException, CsvException, URISyntaxException {
        PersonRepository personRepository = new CsvPersonRepository(Path.of(getClass().getResource("../../../AddressBook").toURI()));
        personService = new PersonServiceImpl(personRepository);
    }

    public Long question1() {
        return personService.howManyMales();
    }

    public Person question2() {
        return personService.getOldest().orElseThrow(() -> new RuntimeException("Could not find oldest person, is the collection empty or are people missing DOB information?"));
    }

    public Long question3() {
        Person bill = personService.getByName("Bill McKnight").orElseThrow(() -> new RuntimeException("Bill McKnight doesn't exist"));
        Person paul = personService.getByName("Paul Robinson").orElseThrow(() -> new RuntimeException("Paul Robinson doesn't exist"));
        return personService.howManyDaysOlder(bill, paul);
    }

    public static void main(String[] args) throws IOException, URISyntaxException, CsvException {

        // Performs instantiation, wiring and inversion of control that Spring would usually provide
        Application application = new Application();
        System.out.println("How many males are in the address book?");
        System.out.println(application.question1());

        System.out.println("Who is the oldest person in the address book?");
        System.out.println(application.question2());

        System.out.println("How many days older is Bill than Paul?");
        System.out.println(application.question3());

    }
}

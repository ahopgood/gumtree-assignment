package com.hopgood.alexander;

import com.hopgood.alexander.model.Person;
import com.opencsv.exceptions.CsvException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.Optional;

public class Application {

    private PersonRepository personRepository;
    private PersonService personService;

    public Application() throws IOException, CsvException, URISyntaxException {
        personRepository = new CsvPersonRepository(Path.of(getClass().getResource("../../../AddressBook").toURI()));
        personService = new PersonServiceImpl(personRepository);
    }

    public void question1() {
        System.out.println("How many males are in the address book?");
        System.out.println(personService.howManyMales());
    }

    public void question2() {
        System.out.println("Who is the oldest person in the address book?");
        System.out.println(personService.getOldest().get());
    }

    public void question3() {
        System.out.println("How many days older is Bill than Paul?");
        Optional<Person> bill = personService.getByName("Bill");
        Optional<Person> paul = personService.getByName("Paul");
        System.out.println(personService.howManyDaysOlder(bill.get(), paul.get()));
    }

    public static void main(String[] args) throws IOException, URISyntaxException, CsvException {

        // Performs instantiation, wiring and inversion of control that Spring would usually provide
        Application application = new Application();
        application.question1();
        application.question2();
        application.question3();

    }
}

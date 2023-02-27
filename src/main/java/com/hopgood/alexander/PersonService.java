package com.hopgood.alexander;

import com.hopgood.alexander.model.Person;
import java.util.Optional;

public interface PersonService {

    Long howManyMales();

    Optional<Person> getOldest();

    Long howManyDaysOlder(Person first, Person second);

    Optional<Person> getByName(String partialName);
}

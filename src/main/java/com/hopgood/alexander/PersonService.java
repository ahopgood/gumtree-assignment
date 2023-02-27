package com.hopgood.alexander;

import com.hopgood.alexander.model.Person;

public interface PersonService {

    Integer howManyMales();

    Person getOldest();

    Integer howManyDaysOlder(Person first, Person second);
}

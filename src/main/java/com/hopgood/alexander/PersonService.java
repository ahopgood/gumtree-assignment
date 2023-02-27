package com.hopgood.alexander;

import com.hopgood.alexander.model.Person;

public interface PersonService {

    Long howManyMales();

    Person getOldest();

    Integer howManyDaysOlder(Person first, Person second);
}

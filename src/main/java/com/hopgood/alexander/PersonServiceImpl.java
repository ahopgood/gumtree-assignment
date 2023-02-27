package com.hopgood.alexander;

import com.hopgood.alexander.model.Person;
import lombok.Builder;

@Builder
public class PersonServiceImpl implements PersonService {

    @Override
    public Integer howManyMales() {
        return null;
    }

    @Override
    public Person getOldest() {
        return null;
    }

    @Override
    public Integer howManyDaysOlder(Person first, Person second) {
        return null;
    }
}

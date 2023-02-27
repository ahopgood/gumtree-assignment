package com.hopgood.alexander;

import com.hopgood.alexander.model.Gender;
import com.hopgood.alexander.model.Person;
import lombok.Builder;

@Builder
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    @Override
    public Long howManyMales() {
        return personRepository.getAll()
                .stream()
                .filter(person -> person.getGender() == Gender.MALE)
                .count();
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

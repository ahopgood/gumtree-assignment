package com.hopgood.alexander;

import com.hopgood.alexander.model.Gender;
import com.hopgood.alexander.model.Person;
import java.util.Optional;
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
    public Optional<Person> getOldest() {
        return personRepository.getAll()
                .stream()
                .sorted((person1, person2) -> person1.getDateOfBirth().compareTo(person2.getDateOfBirth()))
                .findFirst();
    }

    @Override
    public Integer howManyDaysOlder(Person first, Person second) {
        return null;
    }
}

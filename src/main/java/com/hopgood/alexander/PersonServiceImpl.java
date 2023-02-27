package com.hopgood.alexander;

import com.hopgood.alexander.model.Gender;
import com.hopgood.alexander.model.Person;
import java.util.Comparator;
import java.util.Optional;
import lombok.Builder;

import static java.time.temporal.ChronoUnit.DAYS;

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
                .sorted(Comparator.comparing(Person::getDateOfBirth))
                .findFirst();
    }

    @Override
    public Long howManyDaysOlder(Person first, Person second) {
        if (first == null || second == null) {
            return null;
        }
        return DAYS.between(first.getDateOfBirth(), second.getDateOfBirth());
    }
}

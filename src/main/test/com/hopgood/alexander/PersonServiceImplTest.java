package com.hopgood.alexander;

import com.hopgood.alexander.model.Gender;
import com.hopgood.alexander.model.Person;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PersonServiceImplTest {

    private final PersonRepository personRepository = mock(PersonRepository.class);

    private final PersonService personService = PersonServiceImpl.builder()
            .personRepository(personRepository)
            .build();

    @Test
    void testHowManyMales() {
        when(personRepository.getAll()).thenReturn(List.of(
                Person.builder().gender(Gender.MALE).build(),
                Person.builder().gender(Gender.FEMALE).build(),
                Person.builder().gender(Gender.MALE).build()
        ));
        assertThat(personService.howManyMales()).isEqualTo(2);
    }

    @Test
    void testHowManyMales_givenNoMales() {
        when(personRepository.getAll()).thenReturn(List.of(
                Person.builder().gender(Gender.FEMALE).build(),
                Person.builder().gender(Gender.FEMALE).build(),
                Person.builder().gender(Gender.FEMALE).build()
        ));
        assertThat(personService.howManyMales()).isEqualTo(0);
    }

    @Test
    void testHowManyMales_givenEmptyCollection() {
        when(personRepository.getAll()).thenReturn(List.of());
        assertThat(personService.howManyMales()).isEqualTo(0);
    }

    @Test
    void testGetOldest() {
        Person oldest = Person.builder().dateOfBirth(LocalDate.of(2023,1, 1)).build();
        Person middle = Person.builder().dateOfBirth(LocalDate.of(2023, 2, 2)).build();
        Person youngest = Person.builder().dateOfBirth(LocalDate.of(2023, 3, 3)).build();
        when(personRepository.getAll()).thenReturn(List.of(
                oldest,
                youngest,
                middle
        ));
        assertThat(personService.getOldest()).isNotEmpty();
        assertThat(personService.getOldest().get()).isEqualTo(oldest);
    }

    @Test
    void testGetOldest_givenSameAge() {
        Person twin1 = Person.builder().dateOfBirth(LocalDate.of(2023,1, 1)).build();
        Person twin2 = Person.builder().dateOfBirth(LocalDate.of(2023, 1, 1)).build();
        Person youngest = Person.builder().dateOfBirth(LocalDate.of(2023, 3, 3)).build();
        when(personRepository.getAll()).thenReturn(List.of(
                twin1,
                twin2,
                youngest
        ));
        assertThat(personService.getOldest()).isNotEmpty();
        assertThat(personService.getOldest().get()).isEqualTo(twin1);
    }

    @Test
    void testGetOldest_givenEmptyCollection() {
        when(personRepository.getAll()).thenReturn(List.of());
        assertThat(personService.getOldest()).isEmpty();
    }

    @Test
    void testHowManyDaysOlder() {
        Person oldest = Person.builder().dateOfBirth(LocalDate.of(2023,1, 1)).build();
        Person youngest = Person.builder().dateOfBirth(LocalDate.of(2023, 3, 3)).build();
        assertThat(personService.howManyDaysOlder(oldest, youngest)).isEqualTo(61);
    }

    @Test
    void testHowManyDaysOlder_givenPersonIsYounger() {
        Person oldest = Person.builder().dateOfBirth(LocalDate.of(2023,1, 1)).build();
        Person youngest = Person.builder().dateOfBirth(LocalDate.of(2023, 3, 3)).build();
        assertThat(personService.howManyDaysOlder(youngest, oldest)).isEqualTo(-61);
    }

    @Test
    void testHowManyDaysOlder_givenSameAge() {
        Person twin1 = Person.builder().dateOfBirth(LocalDate.of(2023,1, 1)).build();
        Person twin2 = Person.builder().dateOfBirth(LocalDate.of(2023, 1, 1)).build();
        assertThat(personService.howManyDaysOlder(twin1, twin2)).isEqualTo(0);
    }

    @Test
    void testHowManyDaysOlder_nullFirstPerson() {
        Person oldest = Person.builder().dateOfBirth(LocalDate.of(2023,1, 1)).build();
        assertThat(personService.howManyDaysOlder(oldest, null)).isEqualTo(null);
    }

    @Test
    void testHowManyDaysOlder_nullSecondPerson() {
        Person youngest = Person.builder().dateOfBirth(LocalDate.of(2023, 3, 3)).build();
        assertThat(personService.howManyDaysOlder(null, youngest)).isEqualTo(null);
    }
}
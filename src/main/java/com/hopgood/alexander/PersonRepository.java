package com.hopgood.alexander;

import com.hopgood.alexander.model.Person;
import java.util.List;

public interface PersonRepository {

    List<Person> getAll();
}

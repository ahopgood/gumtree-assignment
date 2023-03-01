# gumtree-assignment
This project makes use of Lombok to assist with creation of boilerplate code (getters, setters, builders, constructors etc).  
Setup guides for IDEs can be found here:
* [IntelliJ](https://projectlombok.org/setup/intellij)
* [Eclipse](https://projectlombok.org/setup/eclipse)
* [Other tools/IDEs](https://projectlombok.org/setup/)

## Running Unit Tests
```
mvn clean test
```
## Running Integration Tests
This will run every build stage prior to the integration tests too.
```
mvn clean verify
```

### Code Coverage
Code coverage is managed by Jacoco and gets run as part of the maven lifecycle
Unit code coverage report is generated as part of the test phase:
```
mvn clean test
```
A report can be found at [target/site/jacoco/index.html](target/site/jacoco/index.html)

An aggregate report including both unit and integration test reports is generated as part of the verify phase:
```
mvn clean verify
```

## Design notes
### Application
The `Application` class is responsible for answering the specific questions asked of the exercise and for service/repository instantiation and dependency injection.
This can be provided by a framework like Spring, for the purposes of this exercise I have chosen manual wiring.  
In cases where a database is used I would take advantage of the Spring Boot framework's database repository, testing and criteria (via hibernate) support. 

### PersonService
* Usage of `findFirst()` in `personService.getByName()` if there are two "Bill McKnight"s then we'll retrieve the first encountered in the List.
* Usage of `findFirst()` in `personService.getOldest()` will return the first encountered person in the case where two people have the same date of birth 

### CsvPersonRepository
* I have used a [DateTimeFormatter](https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html) to format the provided dates of birth.
* As a consequence of the input data **not** being to 4 digits, the modern Java `DateTimeFormatter` assumes the current century "20xx".
* Despite this I am inclined to believe this test data intends `16/03/77` to mean 16th of March 1977 
  * This is something I'd pursue with the Product Owner for clarity
  * Using a rule of thumb as per the [SimpleDateFormat](https://docs.oracle.com/en/java/javase/19/docs/api/java.base/java/text/SimpleDateFormat.html) class
    * A range has been configured via the DateTimeFormatterBuilder to convert two digit years into an appropriate century prefix: `1940 - 2039`
    * The start of this range is 1940
      * 1/1/40 becomes 1940
      * 1/1/39 becomes 2039
      * 1/1/99 becomes 1999
  * This does mean that at some point in the future (2039) our double-digit dates will actually mean 2040 but will present as 1940.  

### Person
* ~~Have used `ZonedDateTime` to ensure no loss of timezone data.~~
  * Overkill since we're dealing only with dates and no times but I prefer to use this class out of an abundance of caution.
  * Since we don't deal with time in this case I've overridden the above decision to use `LocalDate` 
* The source data file has the form `firstname surname` there appears to be no requirement at this time to split these into separate values based on the questions we need to answer
* `Gender` as an enum has been chosen due to the fixed range of gender values provided; Male and Female.
  * This can be expanded to provide more inclusive gender values
* The `Person` objects are immutable to prevent against accidental mutation
  * Hence the use of only `@Getter` for retrieving data and `@Builder` for construction
  * Once constructed from our source they should not change
  * This isn't to say a mapper could not create a new instance via a builder but it would have to be a conscious decision.
* `@EqualsAndHashCode` has been added to ensure consistent behaviours with collections and comparisons.
* `@ToString` has been added to assist with readability when debugging.
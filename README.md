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
mvn clean install
```

## Design notes
### Person
* Have used `ZonedDateTime` to ensure no loss of timezone data.
  * Overkill since we're dealing only with dates and no times but I prefer to use this class out of an abundance of caution
* The source data file has the form `firstname surname` there appears to be no requirement at this time to split these into separate values based on the questions we need to answer
* `Gender` as an enum has been chosen due to the fixed range of gender values provided; Male and Female.
  * This can be expanded to provide more inclusive gender values
* The `Person` objects are immutable to prevent against accidental mutation
  * Hence the use of only `@Getter` for retrieving data and `@Builder` for construction
  * Once constructed from our source they should not change
  * This isn't to say a mapper could not create a new instance via a builder but it would have to be a conscious decision.
* `@EqualsAndHashCode` has been added to ensure consistent behaviours with collections and comparisons.
* `@ToString` has been added to assist with readability when debugging.
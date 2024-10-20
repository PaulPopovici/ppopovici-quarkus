# Quarkus demo: Migrating kitchensink from JBoss EAP to Quarkus platform and Java 21

This is an example that illustrates how we can use the Hibernate ORM REST Data with Panache extension to automatically generate the CRUD endpoints for your entities and repositories.

Under the hood this is using:
 - RESTEasy Reactive to expose the REST endpoints
 - Hibernate ORM to perform the CRUD operations on the database
 - A H2 in memory database

## Requirements

To compile and run this demo you will need:

- JDK 21

### Configuring JDK 21

Make sure that `JAVA_HOME` environment variables have
been set, and that a JDK 21 `java` command is on the path.

## Building the demo

Launch the Maven build on the checked out sources of this demo:

> ./mvnw package

## Running the demo

### Live coding with Quarkus

The Maven Quarkus plugin provides a development mode that supports
live coding. To try this out:

> ./mvnw quarkus:dev

In this mode you can make changes to the code and have the changes immediately applied, by just refreshing your browser.

Hot reload works even when modifying your JPA entities.
Try it! Even the database schema will be updated on the fly.

### Run Quarkus in JVM mode

When you're done iterating in developer mode, you can run the application as a
conventional jar file.

First compile it:

> ./mvnw package

Then run it:

> java -jar ./target/quarkus-app/quarkus-run.jar


## See the demo in your browser

Navigate to:

<http://localhost:8080/q/dev-ui/endpoints>

to see exposed endpoints.


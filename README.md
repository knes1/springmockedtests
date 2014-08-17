# Spring Mocked Integration Tests

*Reducing a bit of boilerplate in integration tests that contain mocks in Spring Framework using JUnit and Mockito*

This is a demo/sample app to show an idea how to write concise integration tests in Spring Framework that include a mix
of Spring managed beans and beans mocked with Mockito.

The most interesting stuff happens in [UserServiceImplIntegrationTest](src/test/java/com/knesek/springmockedtests/service/impl/UserServiceImplIntegrationTest.java).
The classes used for simplified mocking are [MockedBenas](src/test/java/com/knesek/springmockedtests/com/knesek/springmockedtests/util/MockedBeans.java)
and [MockImportRegistrar](src/test/java/com/knesek/springmockedtests/com/knesek/springmockedtests/util/MockImportRegistrar.java).

Aside from that, it shows how to create standalone console application with:

- Spring Framework
- JPA / Hibernate
- Spring Data
- Tested with JUnit and Mockito

I hope you find it useful!

--
Kresimir
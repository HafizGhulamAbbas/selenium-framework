### Framework Design

##### Goals:
- Scalable and extensible
- Reusable Rest Assured specifications
- Reusable Rest Assure API requests
- Separation of API layer from test layer
- POJOs for serialization and Deserialization
- Singleton Design Pattern
- Lombok for reducing Boilerplate code
- Builder pattern for Setter methods in POJOs
- Robust reporting and logging using Allure
- Automate positive and negative scenarios
- Support parallel execution
- Data driven using TestNG Data Provider
- Automated access token renewal
- Maven command line execution
- Integration with Git
- Integration with Jenkins

##### Tools and Technologies
- Rest Assured
- TestNG
- JAVA
- Allure Reports
- Hamcrest
- Jackson API
- Lombok
- Github
- Jenkins


### Rest Assured - Setup
Rest Assured is Java library that can automate rest/restful APIs. It uses Java DSL (Domain Specific Language) --> Given, when, then syntax. It can be used with JUnit, TestNG and Cucumber framework.
https://rest-assured.io/

##### Setup pre-requisites:
- JDK
- TestNG (as unit testing framework)
- IDE - IntelliJ
- Maven

## Notes

- You should place rest-assured before the JUnit dependency declaration in your **pom.xml / build.gradle** in order to make sure that the correct version of **Hamcrest** is used.
- REST Assured includes **JsonPath** and **XmlPath** as transitive dependencies
- TestNG by default disables loading DTD from unsecured Urls. If you need to explicitly load the DTD from a http url, please do so by using the JVM argument [-Dtesting.dtd.http=true]
- To do so, right click project > Modify Run Configuration > JDK Settings > VM Options > append '-ea -Dtesting.dtd.http=true'

##### **Static imports**: 

  In order to use REST assured effectively it's recommended to statically import methods from the following classes:
  - io.restassured.RestAssured.*
  - io.restassured.matcher.RestAssuredMatchers.*
  - org.hamcrest.Matchers.*
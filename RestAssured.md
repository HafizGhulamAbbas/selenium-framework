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

## Hamcrest importance and popularity
- Hamcrest is a well known assertion library used for unit testing along with JUnit. 
- Hamcrest can be used along with Rest Assured for assertions. 
- Uses matcher classes for making assertions

### Advantages
1. Human readable and in plain english 
2. Code is neat and intuitive 
3. Provides thin methods like "is" and "not", also called as decorators, for more readibility

### Hamcrest Vs TestNG
- Readibility 
- Descriptive error messages 
- Type Safety _`(Get error at compile type)`_

### Collection matchers (List, Array, Map, etc.)
1. hasItem() -> check single element in a collection 
2. not(hasItem()) -> check single element is NOT in a collection 
3. hasItems() -> Check all elements are in a collection _`(Don't do strict checking. For example, if you check two elements are present in a collection of five. It will pass if two required elements are found)`_
4. contains() -> Check all elements are in a collection and in a strict order _`(It does strict checking)`_
5. containsInAnyOrder() -> Check all elements are in a collection and in any order 
6. empty() -> Check if collection is empty 
7. not(emptyArray()) -> Check if the Array is not empty 
###### For maps
8. hasSize() -> Check size of a collection 
9. everyItem(startsWith()) -> Check if every item in a collection starts with specified string 
10. hasKey() -> Map -> Check if Map has the specified key [value is not checked]
11. hasValue() -> Map -> Check if Map has at least one key matching specified value 
12. hasEntry() -> Maps -> Check if Map has the specified key value pair 
13. equalTo(Collections.EMPTY_MAP) -> Maps [Check if empty]
14. allOf() -> Matches if all matchers matches 
15. anyOf() -> Matches if any of the matchers matches

###### For Numbers
1. greaterThanOrEqualTo()
2. lessThan()
3. lessThanOrEqualTo()

###### For String
1. containsString()
2. emptyString()

[Find all matchers at official documentation](http://hamcrest.org/JavaHamcrest/javadoc/1.3/org/hamcrest/Matchers.html)

### Some Questions
**`Q:`** Given below JSON Object, and assertion, will this work?

body("address", empty()) {
    "address": ""
}

**`Answer`**: Yes / **_`No`_** 

**`Explanation`**: empty() can be used only on collection. Here the value is a string, so empty() cannot be used. You can instead use emptyString() or isEmptyString()

**`Q:`** allOff() and anyOff() can be used on Collections

**`Answer`**: True / **_`False`_**

**`Explanation`**: allOff() and anyOff() can only be used on Strings
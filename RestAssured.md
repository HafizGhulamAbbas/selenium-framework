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


### Headers
https://www.iana.org/assignments/message-headers/message-headers.xhtml


### Serialization and de-serialization
         {Serialiaztion}      -->       [Data Storage]      -->      {De- Serialiaztion}
{(Java Object) to (Byte Stream)} --> [File / DB / Memory] --> {(Byte Stream) to (Java Object)}

### Serialization and de-serialization in RestAssure
https://drive.google.com/file/d/1J5g54dOKgCSqMBoYPSzGI6QAo9sMIp-o/view?usp=sharing

### Object Mapping
REST Assured supports mapping Java objects to and from JSON and XML. For JSON you need to have either Jackson, Jackson2, Gson or Johnzon in the classpath and for XML you need Jakarta EE or JAXB.
https://github.com/rest-assured/rest-assured/wiki/Usage#object-mapping


JSON Object:
========================================================
ObjectMapper
CreateObjectNode() —> returns instance of ObjectNode class

ObjectNode
put(String, T)

ObjectNode <-> HashMap

put(String, T) <-> put(String, T)

Get JSON Object as String —> writeValueAsString() from ObjectMapper
Get JSON Object —> writerWithDefaultPrettyPrinter() from ObjectMapper

RestAssured
—> Pass String
—> Pass ObjectNode


JSON Array:
========================================================
ObjectMapper
CreateArrayNode() —> returns instance of ArrayNode class

ArrayNode
put(String, T)

ArrayNode <-> ArrayList

add(T) <-> add(T)

Get JSON Array as String —> writeValueAsString() from ObjectMapper
Get JSON Array —> writerWithDefaultPrettyPrinter() from ObjectMapper

RestAssured
—> Pass String
—> Pass ArrayNode


### Complex POJO
https://sdsglobal.udemy.com/course/rest-assured-api-automation/learn/lecture/25233256#overview


### Request Payload
https://drive.google.com/file/d/1DQUA9FMcwa3RzG_GN8D3DzAdAC-cMyDB/view?usp=sharing

### Request Response Payload
https://drive.google.com/file/d/106tyXpysTSq6D32qWYwgD1AGfzkYHTh_/view?usp=sharing


##### Sample Collection for Which POJO class are created:
```{
    "collection": {
        "info": {
            "name": "Sample Collection {{$randomInt}}",
            "description": "This is just a sample collection.",
            "schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json"
        },
        "item": [
            {
                "name": "This is a folder",
                "item": [
                    {
                        "name": "Sample Post Request",
                        "request": {
                            "url": "https://postman-echo.com/post",
                            "method": "POST",
                            "header": [
                                {
                                    "key": "Content-Type",
                                    "value": "application/json"
                                }
                            ],
                            "body": {
                                "mode": "raw",
                                "raw": "{\"data\": \"123\"}"
                            },
                            "description": "This is a sample POST Request"
                        }
                    }
                ]
            },
            {
                "name": "Sample GET Request",
                "request": {
                    "url": "https://postman-echo/get",
                    "method": "GET",
                    "description": "This is a sample GET Request"
                }
            }
        ]
    }
}```
```
### Different Authentication Schemes
1. Basic
2. Bearer
3. Digest
4. OAuth

###### BASIC
- Base64 encoded username:password in the header. Not secured
- Example: Authorization --> Basic dXNlck5hbWU6TXlQYXNzd29yZA==

###### DIGEST
- Challenge-response paradigm
- Username and password are hashed using MD5 algorithm
- Working: Client sent userName and password, server send some arguments to client to work on, then client will work upon the requested argus in headers and encoded stuff is send to server in headers. 

###### BEARER
- It holds a security token to get a certain resource

###### API KEY
- Usually generated during first time login or signup 
- Used as a replacement for username and password 
- Usually fetched from account settings and often it is possible to delete, regenerate and create multiple API keys 
- Passed as a header or a query parameter or even in the request body 
- Not secured

###### OAuth
- it is related to authorization, not authentication. For authentication, we've OpenId Connect.


#### OAuth - Authorization Grant Flow
- Authorization code grant flow is more secure because the access token is sent over the backend channel
https://drive.google.com/file/d/16OrxS0iCHcfj8RGRJXrgn3gjVcUokHp8/view?usp=sharing

#### OAuth - Implicit Grant Flow
- Implicit grant flow is used for JavaScript applications that run inside the browser without a back end
https://drive.google.com/file/d/1ZVa0QZWvIRSFahzoZJt3U-aAqyIuEX2C/view?usp=sharing

#### OAuth - Client Credentials Flow
- Client credentials flow is suitable for Micro services architecture
https://drive.google.com/file/d/141mBaw7RUe7Ed_TrtjJI2Sk6pGuJXO-Y/view?usp=sharing


### Google OAuth2.0
##### Documentation: https://developers.google.com/identity/protocols/oauth2
##### Playground: https://developers.google.com/oauthplayground/
##### Scopes of Google APIs: https://developers.google.com/identity/protocols/oauth2/scopes
##### Gmail APIs: https://developers.google.com/gmail/api/reference/rest
##### Email format: https://ostermiller.org/calc/encode.html


### Form Based Authentication
- Project URL: https://github.com/dangeabunea/RomanianCoderExamples


# What are we automating?
Spotify Playlists API using the OAuth 2.0 flow [Authorization Code Grant Flow]

- Create a Playlist
- Get a Playlist
- Change a Playlist’s Details

### Test Cases:
- shouldBeAbleToCreateAPlaylist 
- shouldNotBeAbleToCreateAPlaylistWithoutName 
- shouldNotBeAbleToCreateAPlaylistIfTokenIsExpired 
- shouldBeAbleToFetchAGivenPlaylist 
- shouldBeAbleToUpdateAGivenPlaylist

##### Rest Assured Framework Project Structure: 
https://drive.google.com/file/d/1-mMpz1XdkVQKdpKdtkwag4Axr2ZxHxFC/view?usp=sharing

### Spotify OAuth 2.0 Introduction
https://developer.spotify.com/documentation/web-api/


### JSON Schema to POJO convertor
https://www.jsonschema2pojo.org/

### Lombok
https://projectlombok.org/

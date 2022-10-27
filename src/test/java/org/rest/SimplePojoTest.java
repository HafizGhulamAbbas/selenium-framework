package org.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.rest.pojo.simple.SimplePojo;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class SimplePojoTest {
    ResponseSpecification responseSpecification;

    @BeforeClass
    public void beforeClass(){
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().
                setBaseUri("https://4f30687a-8c73-454c-81f7-5601307ad50a.mock.pstmn.io").
                        setContentType(ContentType.JSON).
                        log(LogDetail.ALL);

        RestAssured.requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).
                log(LogDetail.ALL);
        responseSpecification = responseSpecBuilder.build();
    }

    @Test
    public void simple_pojo_example() throws JsonProcessingException {
        SimplePojo simplePojo = new SimplePojo("value1", "value2");
        /*SimplePojo simplePojo = new SimplePojo();
        simplePojo.setKey1("value1");
        simplePojo.setKey2("value2");*/

        SimplePojo deserializedPojo = given().
                body(simplePojo). // here it is doing serialization
        when().
                post("/postSimpleJson").
        then().spec(responseSpecification).
                extract().
                response().
                as(SimplePojo.class); // here it is doing de-serialization

        // Verify request body with response body after converting into java object
        ObjectMapper objectMapper = new ObjectMapper();
        String deserializedPojoStr = objectMapper.writeValueAsString(deserializedPojo);
        String simplePojoStr = objectMapper.writeValueAsString(simplePojo);
        assertThat(objectMapper.readTree(deserializedPojoStr), equalTo(objectMapper.readTree(simplePojoStr)));

    }
}

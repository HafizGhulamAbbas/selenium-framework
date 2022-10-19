package org.rest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class RequestSpecificationExample {
    RequestSpecification requestSpecification;

    @BeforeClass
    public void beforeClass() {
        // the difference between given() and with() is syntactical.
        requestSpecification = with().
                baseUri("https://api.postman.com").
                header("x-api-key", "PMAK-634eeca9391e9e36398b8cbb-a73e5dfd984bf78ddadb401fe33e0d6773");
    }

    @Test
    public void validate_status_code(){
        // given(requestSpecification).
        given().spec(requestSpecification).
        when().
                get("/workspaces").
        then().
                log().all().
                assertThat().
                statusCode(200);
    }
    @Test
    public void validate_response_body(){
        given().spec(requestSpecification).
        when().
                get("/workspaces").
        then().
                log().all().
                assertThat().
                statusCode(200);
    }
}

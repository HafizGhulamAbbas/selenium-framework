package org.rest;

import io.restassured.config.EncoderConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;

public class RequestParameters {

    @Test
    public void single_query_parameter(){
        given().
                baseUri("https://postman-echo.com").
                param("foo1", "bar1"). //generic
                // queryParam("foo2", "bar2"). // specific
                log().all().
        when().
                get("/get").
        then().
                log().all().
                assertThat().
                statusCode(200);
    }
}

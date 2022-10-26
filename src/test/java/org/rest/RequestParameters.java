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

    @Test
    public void multiple_query_parameters(){
        HashMap<String, String> queryParams = new HashMap<>();
        queryParams.put("foo1", "bar1");
        queryParams.put("foo2", "bar2");
        given().
                baseUri("https://postman-echo.com").
                // param("foo3", "bar3"). // Request Params
                // queryParam("foo1", "bar1").
                // queryParam("foo2", "bar2").
                queryParams(queryParams). // Query Params
                log().all().
        when().
                get("/get").
        then().
                log().all().
                assertThat().
                statusCode(200);
    }

    @Test
    public void multi_value_query_parameter(){
        given().
                baseUri("https://postman-echo.com").
                queryParam("foo1", "bar1;bar2;bar3").
                log().all().
        when().
                get("/get").
        then().
                log().all().
                assertThat().
                statusCode(200);
    }
}

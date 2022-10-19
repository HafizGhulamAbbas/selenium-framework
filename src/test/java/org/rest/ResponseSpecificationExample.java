package org.rest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class ResponseSpecificationExample {
    @BeforeClass
    public void beforeClass(){
/*        requestSpecification = with().
                baseUri("https://api.postman.com").
                header("X-Api-Key", "PMAK-5ff2d720d2a39a004250e5da-c658c4a8a1cee3516762cb1a51cba6c5e2").
                log().all();*/
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri("https://api.postman.com");
        requestSpecBuilder.addHeader("x-api-key", "PMAK-634eeca9391e9e36398b8cbb-a73e5dfd984bf78ddadb401fe33e0d6773");
        requestSpecBuilder.log(LogDetail.ALL);

        RestAssured.requestSpecification = requestSpecBuilder.build();
    }

    @Test
    public void validate_status_code() {
        Response response = get("/workspaces").
        then().
                log().all().
                contentType(ContentType.JSON).
                extract().
                response();
        assertThat(response.statusCode(), is(equalTo(200)));
    }

    @Test
    public void validate_response_body() {
        Response response = get("/workspaces").
                then().
                log().all().
                contentType(ContentType.JSON).
                extract().
                response();
        assertThat(response.statusCode(), is(equalTo(200)));
        assertThat(response.path("workspaces[2].name").toString(), equalTo("rest-assured-framework"));
    }
}

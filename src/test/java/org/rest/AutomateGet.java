package org.rest;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AutomateGet {

    @Test
    public void validate_status_code(){
        given().
                baseUri("https://api.postman.com").
                header("x-api-key", "PMAK-634eeca9391e9e36398b8cbb-a73e5dfd984bf78ddadb401fe33e0d6773").
        when().
                get("/workspaces").
        then().
                log().all(). // To show full response. Always use it before assertThat() function. Otherwise, it will not show response.
                assertThat().
                statusCode(200);
    }
}

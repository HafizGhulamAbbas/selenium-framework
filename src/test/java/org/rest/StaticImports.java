package org.rest;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class StaticImports {

    @Test
    public void simple_test_case(){
        given().
                baseUri("https://api.postman.com").
                header("x-api-key", "PMAK-634eeca9391e9e36398b8cbb-a73e5dfd984bf78ddadb401fe33e0d6773").
        when().
                get("/workspaces").
        then().
                statusCode(200).
                body("name", is(equalTo("Ghulam Abbas")),
                        "email", is(equalTo("hgabbas1122@gmail.com")));
    }
}

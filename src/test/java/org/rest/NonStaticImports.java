package org.rest;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class NonStaticImports {

    @Test
    public void simple_test_case(){
        RestAssured.given().
                baseUri("https://api.postman.com").
                header("x-api-key", "PMAK-634eeca9391e9e36398b8cbb-a73e5dfd984bf78ddadb401fe33e0d6773").
        when().
                get("/workspaces").
        then().
                statusCode(200).
                body("name", Matchers.is(Matchers.equalTo("Ghulam Abbas")),
                        "email", Matchers.is(Matchers.equalTo("hgabbas1122@gmail.com")));
    }
}

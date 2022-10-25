package org.rest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.matchesPattern;

public class AutomatePut {

    @BeforeClass
    public void beforeClass(){
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().
                setBaseUri("https://api.postman.com").
                addHeader("x-api-key", "PMAK-634eeca9391e9e36398b8cbb-a73e5dfd984bf78ddadb401fe33e0d6773").
                setContentType(ContentType.JSON).
                log(LogDetail.ALL);
        RestAssured.requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).
                log(LogDetail.ALL);
        RestAssured.responseSpecification = responseSpecBuilder.build();
    }

    @Test
    public void validate_put_request_bdd_style(){
        String workspaceId = "6ebb546d-f945-43b8-a74a-ecbdb704fd40";
        String payload = "{\n" +
                "    \"workspace\": {\n" +
                "        \"name\": \"my new workspace - edit\",\n" +
                "        \"type\": \"personal\",\n" +
                "        \"description\": \"this is created by Rest Assured\"\n" +
                "    }\n" +
                "}";
        given().
                body(payload).
                pathParam("workspaceId", workspaceId).
        when().
                put("/workspaces/{workspaceId}").
        then().
                log().all().
                assertThat().
                body("workspace.name", equalTo("my new workspace - edit"),
                        "workspace.id", matchesPattern("^[a-z0-9-]{36}$"),
                        "workspace.id", equalTo(workspaceId));;
    }
}

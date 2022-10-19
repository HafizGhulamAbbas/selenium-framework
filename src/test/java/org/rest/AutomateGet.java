package org.rest;

import io.restassured.config.LogConfig;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

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

    // Now let's verify the response body. We are to assert each workspaces' name, type, and visibility.
    // We will do it using Groovy/GPath expression
    // Response object is below
    /* {
        "workspaces": [
        {
            "id": "80c02df0-4832-4484-8d3d-6a64d598010a",
                "name": "My Workspace",
                "type": "personal",
                "visibility": "only-me"
        },
        {
            "id": "8f9fba5a-bc93-469e-8403-ef9e4a2f606c",
                "name": "Team Workspace",
                "type": "team",
                "visibility": "team"
        },
        {
            "id": "15ee1f23-06f2-40c1-a85c-2f314f0e7d79",
                "name": "rest-assured-framework",
                "type": "team",
                "visibility": "team"
        }
    ]
    }*/
    @Test
    public void validate_response_body(){
        given().
                baseUri("https://api.postman.com").
                header("x-api-key", "PMAK-634eeca9391e9e36398b8cbb-a73e5dfd984bf78ddadb401fe33e0d6773").
        when().
                get("/workspaces").
        then().
                log().all().
                assertThat().
                statusCode(200).
                body("workspaces.name", hasItems("Team Workspace", "My Workspace", "rest-assured-framework"),
                        "workspaces.type", hasItems("team", "personal", "personal"),
                        "workspaces[0].name", equalTo("My Workspace"),
                        "workspaces[1].name", is(equalTo("Team Workspace")),
                        "workspaces[2].name", is(equalTo("rest-assured-framework")),
                        "workspaces[0].visibility", equalTo("only-me"),
                        "workspaces[1].visibility", is(equalTo("team")),
                        "workspaces[2].visibility", is(equalTo("team")),
                        "workspaces.size()", equalTo(3)
                );
    }

    // If you want to extract response so that you can assert in other assertion library instead of Hamscrest like TestNG
    // The entire response object will include headers, cookies, and body etc
    @Test
    public void extract_response(){
        Response res = given().
                baseUri("https://api.postman.com").
                header("x-api-key", "PMAK-634eeca9391e9e36398b8cbb-a73e5dfd984bf78ddadb401fe33e0d6773").
        when().
                get("/workspaces").
        then().
                assertThat().
                statusCode(200).
                // Below two lines will work and the response is saved in res, and we can manipulate it accordingly. Note, Response is an interface, so no need to create object. res will contain the ref.
                extract().
                response();
        System.out.println("response = " + res.asString());
    }

    // Let's extract a single value from response. We will use Gpath expression. Rest Assured auto-detects whether to use JsonPath or XMLpath based on the content type.
    // If no content path, Rest Assured will try to look at the 'default parser'.
    @Test
    public void extract_single_value_from_response(){
        Response res = given().
                baseUri("https://api.postman.com").
                header("x-api-key", "PMAK-634eeca9391e9e36398b8cbb-a73e5dfd984bf78ddadb401fe33e0d6773").
            when().
                get("/workspaces").
            then().
                assertThat().
                statusCode(200).
                extract().
                response();
        System.out.println("workspace name: " + res.path("workspaces[2].name"));

        // Second way
        /* JsonPath jsonPath = new JsonPath(res.asString());
        System.out.println("workspace name: " + jsonPath.getString("workspaces[2].name")); */

        // Third way
        /*String response = given().
                baseUri("https://api.postman.com").
                header("x-api-key", "PMAK-634eeca9391e9e36398b8cbb-a73e5dfd984bf78ddadb401fe33e0d6773").
        when().
                get("/workspaces").
        then().
                assertThat().
                statusCode(200).
                extract().
                response().asString();
        System.out.println("workspace name: " + JsonPath.from(response).getString("workspaces[2].name"));*/

        // Fourth way
        /*String name = given().
                baseUri("https://api.postman.com").
                header("x-api-key", "PMAK-634eeca9391e9e36398b8cbb-a73e5dfd984bf78ddadb401fe33e0d6773").
        when().
                get("/workspaces").
        then().
                assertThat().
                statusCode(200).
                extract().
                response().path("workspaces[2].name");
        System.out.println("workspace name: " + name);*/
    }

    // Hamscrest / TestNG Assertion on the extracted response
    @Test
    public void hamcrest_assert_on_extracted_response(){
        String name = given().
                baseUri("https://api.postman.com").
                header("x-api-key", "PMAK-634eeca9391e9e36398b8cbb-a73e5dfd984bf78ddadb401fe33e0d6773").
                when().
                get("/workspaces").
                then().
                assertThat().
                statusCode(200).
                extract().
                response().path("workspaces[2].name");
        System.out.println("workspace name = " + name);

        // assertThat(name, equalTo("rest-assured-framework")); // Hamscrest
        Assert.assertEquals(name, "rest-assured-framework"); // TestNG
    }

    // Hamcrest assertion practice
    @Test
    public void validate_response_body_hamcrest_learnings() {
        given().
                baseUri("https://api.postman.com").
                header("x-api-key", "PMAK-634eeca9391e9e36398b8cbb-a73e5dfd984bf78ddadb401fe33e0d6773").
        when().
                get("/workspaces").
        then().
                // log().all().
                assertThat().
                statusCode(200).
                body("workspaces.name", containsInAnyOrder("Team Workspace", "My Workspace", "rest-assured-framework"),
                        "workspaces.name", is(not(emptyArray())),
                        "workspaces.name", hasSize(3),
                        // "workspaces.name", everyItem(startsWith("My"))
                        "workspaces[2]", hasKey("id"),
                        "workspaces[2]", hasValue("rest-assured-framework"),
                        "workspaces[0]", hasEntry("id", "80c02df0-4832-4484-8d3d-6a64d598010a"),
                        "workspaces[0]", not(equalTo(Collections.EMPTY_MAP)),
                        // "workspaces[0]", not(equalTo(Collections.EMPTY_LIST)),
                        // "workspaces[0]", not(equalTo(Collections.emptySet())),
                        "workspaces[0].name", allOf(startsWith("My"), containsString("Workspace"))
                );
    }

    @Test
    public void request_response_logging(){
        given().
                baseUri("https://api.postman.com").
                header("x-api-key", "PMAK-634eeca9391e9e36398b8cbb-a73e5dfd984bf78ddadb401fe33e0d6773").
                log().all().
                // log().headers().
                // log().body().
                // log().cookies().
        when().
                get("/workspaces").
        then().
                log().all().
                // log().headers().
                // log().body().
                // log().cookies().
                assertThat().
                statusCode(200);
    }

    @Test
    public void log_only_if_validation_fail(){
        given().
                baseUri("https://api.postman.com").
                header("x-api-key", "PMAK-634eeca9391e9e36398b8cbb-a73e5dfd984bf78ddadb401fe33e0d6773").
                config(config.logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails())).
                // OR
                // log().ifValidationFails().
        when().
                get("/workspaces").
        then().
                // log().ifValidationFails().
                assertThat().
                statusCode(200);
    }

    @Test
    public void log_blacklist_header(){
        Set<String> headers = new HashSet<String>();
        headers.add("x-api-key");
        headers.add("Accept");
        given().
                baseUri("https://api.postman.com").
                header("x-api-key", "PMAK-634eeca9391e9e36398b8cbb-a73e5dfd984bf78ddadb401fe33e0d6773").
                // config(config.logConfig(LogConfig.logConfig().blacklistHeader("x-api-key"))).
                // Or if you want to blacklist multiple headers
                config(config.logConfig(LogConfig.logConfig().blacklistHeaders(headers))).
                log().all().
        when().
                get("/workspaces").
        then().
                assertThat().
                statusCode(200);
    }
}

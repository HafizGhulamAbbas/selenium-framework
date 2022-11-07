package org.spotify.oauth2.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.spotify.oauth2.api.SpecBuilder.getRequestSpec;
import static org.spotify.oauth2.api.SpecBuilder.getResponseSpec;

public class RestResource {

    public static Response post(String path, String access_token, Object requestPlaylist) {
        return given(getRequestSpec()).
                body(requestPlaylist).
                header("Authorization", "Bearer " + access_token).
        when().
                post(path).
        then().spec(getResponseSpec()).
                extract().
                response();
    }

    public static Response postAccount(HashMap<String, String> formParams) {
        return given().
                baseUri("https://accounts.spotify.com").
                contentType(ContentType.URLENC).
                formParams(formParams).
                log().all().
        when().
                post("/api/token").
        then().spec(getResponseSpec()).
                extract().
                response();
    }

    public static Response get(String path, String access_token) {
        return given(getRequestSpec()).
                header("Authorization", "Bearer " + access_token).
        when().
                get(path).
        then().spec(getResponseSpec()).
                extract().
                response();
    }

    public static Response update(String path, String access_token, Object requestPlaylist) {
        return given(getRequestSpec()).
                header("Authorization", "Bearer " + access_token).
                body(requestPlaylist).
        when().
                put(path).
        then().spec(getResponseSpec()).
                extract().
                response();
    }
}

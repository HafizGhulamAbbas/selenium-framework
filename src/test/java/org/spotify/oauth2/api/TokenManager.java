package org.spotify.oauth2.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.spotify.oauth2.api.SpecBuilder.getResponseSpec;

public class TokenManager {

    public static String renewToken() {
        HashMap<String, String> formParams = new HashMap<String, String>();
        formParams.put("client_id", "189434f2382e4d799a65be47178dc725");
        formParams.put("client_secret", "083ec36921c141029d590e5949980f87");
        formParams.put("grant_type", "refresh_token");
        formParams.put("refresh_token", "AQBGzXamGAJzhl915c2O5HUtOq710vYwlfY7HfBgX-au0F9_g7uJxEU7_ycGR8qJOKRmcjiX_l_OQapVK-nVZjD34UscuwyGoqxKSEFmJBHX-vBk6gESNb0u6CE5gdx94co");

        Response response = given().
                baseUri("https://accounts.spotify.com").
                contentType(ContentType.URLENC).
                formParams(formParams).
        when().
                post("/api/token").
        then().spec(getResponseSpec()).
                extract().
                response();

        if(response.statusCode() != 200) {
            throw new RuntimeException("ABORT! Failed to fetch new token.");
        }
        return response.path("access_token");
    }

}

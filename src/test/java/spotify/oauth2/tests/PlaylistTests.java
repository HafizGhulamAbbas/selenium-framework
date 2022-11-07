package spotify.oauth2.tests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

public class PlaylistTests {
    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;
    String access_token = "BQDpn_JR1fXEMWkJBCA83gF5O4TmiV1sP9TpD8aqHf73hA7XGB0QfWLoc8Sy4b-NFyNYUjclY4ebO8qQN6e851MHegoG-MiytzoXP-_nOAvCNmFE4fVGUWzO2-z2s7hiVqicPXU0AiDD7sm9e_KSauD0SLWFpC__cU7YHjTZQJYTBtlANBu-OMKONiyWRQiz7KnhCXNThyLOUf3KIifXtnNxRoUfO6QIbhyk6KLVNK_PFzOW3PCzPn7ALAxbEp4N3oBkwoPXgsiiTnOz";

    @BeforeClass
    public void beforeClass() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().
                setBaseUri("https://api.spotify.com").
                setBasePath("/v1").
                addHeader("Authorization", "Bearer " + access_token).
                setContentType(ContentType.JSON).
                log(LogDetail.ALL);

        requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
                expectContentType(ContentType.JSON).
                log(LogDetail.ALL);
        responseSpecification = responseSpecBuilder.build();
    }

    @Test
    public void shouldBeAbleToCreateAPlaylist() {
        String payload = "{\n" +
                "  \"name\": \"New Playlist\",\n" +
                "  \"description\": \"New playlist description\",\n" +
                "  \"public\": false\n" +
                "}";
        given(requestSpecification).
                body(payload).
        when().
                post("/users/3127ntzuaopscs44bj3jokvgmss4/playlists").
        then().spec(responseSpecification).
                assertThat().
                statusCode(201).
                body("name", equalTo("New Playlist"),
                        "description", equalTo("New playlist description"),
                        "public", equalTo(false));
    }

    @Test
    public void shouldBeAbleToGetAPlaylist() {
        given(requestSpecification).
        when().
                get("/playlists/0EftGzFkFNeFyKqtrkT1Qm").
        then().spec(responseSpecification).
                assertThat().
                statusCode(200).
                body("name", equalTo("New Playlist"),
                        "description", equalTo("New playlist description"),
                        "public", equalTo(false));
    }
}

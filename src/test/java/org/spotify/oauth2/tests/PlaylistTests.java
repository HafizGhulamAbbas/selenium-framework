package org.spotify.oauth2.tests;

import io.restassured.http.ContentType;
import org.spotify.oauth2.pojo.Error;
import org.spotify.oauth2.pojo.Playlist;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.spotify.oauth2.api.SpecBuilder.getRequestSpec;
import static org.spotify.oauth2.api.SpecBuilder.getResponseSpec;

public class PlaylistTests {

    @Test
    public void shouldBeAbleToCreateAPlaylist() {
        Playlist requestPlaylist = new Playlist().
                setName("New Playlist").
                setDescription("New playlist description").
                setPublic(false);

        Playlist responsePlaylist = given(getRequestSpec()).
                body(requestPlaylist).
        when().
                post("/users/3127ntzuaopscs44bj3jokvgmss4/playlists").
        then().spec(getResponseSpec()).
                assertThat().
                statusCode(201).
                extract().
                response().
                as(Playlist.class);

        assertThat(responsePlaylist.getName(), equalTo(responsePlaylist.getName()));
        assertThat(responsePlaylist.getDescription(), equalTo(responsePlaylist.getDescription()));
        assertThat(responsePlaylist.getPublic(), equalTo(responsePlaylist.getPublic()));
    }

    @Test
    public void shouldBeAbleToGetAPlaylist() {
        Playlist requestPlaylist = new Playlist().
                setName("New Playlist").
                setDescription("New playlist description").
                setPublic(false);

        Playlist responsePlaylist = given(getRequestSpec()).
        when().
                get("/playlists/3bW3BNOhxJiAjtBhcsV7iF").
        then().spec(getResponseSpec()).
                assertThat().
                statusCode(200).
                extract().
                response().
                as(Playlist.class);

        assertThat(responsePlaylist.getName(), equalTo(responsePlaylist.getName()));
        assertThat(responsePlaylist.getDescription(), equalTo(responsePlaylist.getDescription()));
        assertThat(responsePlaylist.getPublic(), equalTo(responsePlaylist.getPublic()));
    }

    @Test
    public void shouldBeAbleToUpdateAPlaylist() {
        Playlist requestPlaylist = new Playlist().
                setName("Updated Playlist Name").
                setDescription("Updated playlist description").
                setPublic(false);

        given(getRequestSpec()).
                body(requestPlaylist).
        when().
                put("/playlists/3bW3BNOhxJiAjtBhcsV7iF").
        then().spec(getResponseSpec()).
                assertThat().
                statusCode(200);
    }

    @Test
    public void shouldNotBeAbleToCreateAPlaylistWithoutName() {
        Playlist requestPlaylist = new Playlist().
                setName("").
                setDescription("New playlist description").
                setPublic(false);

        Error error =  given(getRequestSpec()).
                body(requestPlaylist).
        when().
                post("/users/3127ntzuaopscs44bj3jokvgmss4/playlists").
        then().spec(getResponseSpec()).
                assertThat().
                statusCode(400).
                extract().
                response().
                as(Error.class);

        assertThat(error.getError().getStatus(), equalTo(400));
        assertThat(error.getError().getMessage(), equalTo("Missing required field: name"));
    }

    @Test
    public void shouldNotBeAbleToCreateAPlaylistWithExpiredToken() {
        Playlist requestPlaylist = new Playlist().
                setName("New Playlist").
                setDescription("New playlist description").
                setPublic(false);

        Error error =  given().
                baseUri("https://api.spotify.com").
                basePath("/v1").
                header("Authorization", "Bearer " + "invalidAccessToken").
                contentType(ContentType.JSON).
                log().all().
                body(requestPlaylist).
        when().
                post("/users/3127ntzuaopscs44bj3jokvgmss4/playlists").
        then().spec(getResponseSpec()).
                assertThat().
                statusCode(401).
                extract().
                as(Error.class);

        assertThat(error.getError().getStatus(), equalTo(401));
        assertThat(error.getError().getMessage(), equalTo("Invalid access token"));
    }
}

package org.spotify.oauth2.tests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.spotify.oauth2.pojo.Error;
import org.spotify.oauth2.pojo.Playlist;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PlaylistTests {
    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;
    String access_token = "BQC1_cnuii8OT1rI81oPy__8z7k6FAtWphGQeoY2vW3d1NiBVaG5jBoPQIbC73OyKWWSCSz-2gd_JFx26ytcT6pKwmj10KKaGcH2isGW8EXG7H8E28hLRg36yQaO-XenF3zuW018nWhkKdBG26fXdH3ip0bod7_7VoaihkaOKkreSfbLFMrN4J2RtFVylcaoYRNAkyPrSDe7bnpUacDzmBd_ssZO0bhSTqgjSiV9iGKYsY_eA1E0u3soBuBOeqhjC-15SdfBTu2ybEMd";

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
                log(LogDetail.ALL);
        responseSpecification = responseSpecBuilder.build();
    }

    @Test
    public void shouldBeAbleToCreateAPlaylist() {
        Playlist requestPlaylist = new Playlist().
                setName("New Playlist").
                setDescription("New playlist description").
                setPublic(false);

        Playlist responsePlaylist = given(requestSpecification).
                body(requestPlaylist).
        when().
                post("/users/3127ntzuaopscs44bj3jokvgmss4/playlists").
        then().spec(responseSpecification).
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

        Playlist responsePlaylist = given(requestSpecification).
        when().
                get("/playlists/3bW3BNOhxJiAjtBhcsV7iF").
        then().spec(responseSpecification).
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

        given(requestSpecification).
                body(requestPlaylist).
        when().
                put("/playlists/3bW3BNOhxJiAjtBhcsV7iF").
        then().spec(responseSpecification).
                assertThat().
                statusCode(200);
    }

    @Test
    public void shouldNotBeAbleToCreateAPlaylistWithoutName() {
        Playlist requestPlaylist = new Playlist().
                setName("").
                setDescription("New playlist description").
                setPublic(false);

        Error error =  given(requestSpecification).
                body(requestPlaylist).
        when().
                post("/users/3127ntzuaopscs44bj3jokvgmss4/playlists").
        then().spec(responseSpecification).
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
        then().spec(responseSpecification).
                assertThat().
                statusCode(401).
                extract().
                as(Error.class);

        assertThat(error.getError().getStatus(), equalTo(401));
        assertThat(error.getError().getMessage(), equalTo("Invalid access token"));
    }
}

package org.spotify.oauth2.tests;

import io.qameta.allure.*;
import io.restassured.response.Response;
import org.spotify.oauth2.api.applicationApi.PlaylistApi;
import org.spotify.oauth2.pojo.Error;
import org.spotify.oauth2.pojo.Playlist;
import org.spotify.oauth2.utils.DataLoader;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.spotify.oauth2.utils.FakerUtils.generateDescription;
import static org.spotify.oauth2.utils.FakerUtils.generateName;

@Epic("Spotify OAuth 2.0")
@Feature("Playlist API")
public class PlaylistTests {

    @Story("create a playlist story")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @TmsLink("12345")
    @Issue("134")
    @Description("This is description")
    @Test(description = "Should be able to create a playlist")
    public void shouldBeAbleToCreateAPlaylist() {
        Playlist requestPlaylist = playlistBuilder(generateName(), generateDescription(), false);
        Response response = PlaylistApi.post(requestPlaylist);
        assertStatusCode(response.statusCode(), 201);
        assertPlaylistEqual(response.as(Playlist.class), requestPlaylist);
    }

    @Test
    public void shouldBeAbleToGetAPlaylist() {
        Playlist requestPlaylist = playlistBuilder("Updated Playlist Name", "Updated playlist description", false);
        Response response = PlaylistApi.get(DataLoader.getInstance().getGetPlaylistId());
        assertStatusCode(response.statusCode(), 200);
        assertPlaylistEqual(response.as(Playlist.class), requestPlaylist);
    }

    @Test
    public void shouldBeAbleToUpdateAPlaylist() {
        Playlist requestPlaylist = playlistBuilder(generateName(), generateDescription(), false);
        Response response = PlaylistApi.update(DataLoader.getInstance().getUpdatePlaylistId(), requestPlaylist);
        assertStatusCode(response.statusCode(), 200);
    }

    @Story("create a playlist story")
    @Test
    public void shouldNotBeAbleToCreateAPlaylistWithoutName() {
        Playlist requestPlaylist = playlistBuilder("", generateDescription(), false);
        Response response = PlaylistApi.post(requestPlaylist);
        assertStatusCode(response.statusCode(), 400);
        assertError(response.as(Error.class), 400, "Missing required field: name");
    }

    @Story("create a playlist story")
    @Test
    public void shouldNotBeAbleToCreateAPlaylistWithExpiredToken() {
        String invalid_token = "invalidAccessToken";
        Playlist requestPlaylist = playlistBuilder(generateName(), generateDescription(), false);
        Response response = PlaylistApi.post(invalid_token, requestPlaylist);
        assertStatusCode(response.statusCode(), 401);
        assertError(response.as(Error.class), 401, "Invalid access token");
    }

    @Step
    public Playlist playlistBuilder(String name, String description, Boolean _public) {
        return Playlist.builder().
                name(name).
                description(description).
                _public(_public).
                build();
    }

    @Step
    public void assertPlaylistEqual(Playlist responsePlaylist, Playlist requestPlaylist) {
        assertThat(responsePlaylist.getName(), equalTo(requestPlaylist.getName()));
        assertThat(responsePlaylist.getDescription(), equalTo(requestPlaylist.getDescription()));
        assertThat(responsePlaylist.get_public(), equalTo(requestPlaylist.get_public()));
    }

    @Step
    public void assertStatusCode(int actualStatusCode, int expectedStatusCode) {
        assertThat(actualStatusCode, equalTo(expectedStatusCode));
    }

    @Step
    public void assertError(Error responseError, int expectedStatusCode, String expectedMessage) {
        assertThat(responseError.getError().getStatus(), equalTo(expectedStatusCode));
        assertThat(responseError.getError().getMessage(), equalTo(expectedMessage));
    }
}

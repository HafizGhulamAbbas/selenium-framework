package org.spotify.oauth2.tests;

import io.restassured.response.Response;
import org.spotify.oauth2.api.applicationApi.PlaylistApi;
import org.spotify.oauth2.pojo.Error;
import org.spotify.oauth2.pojo.Playlist;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PlaylistTests {

    @Test
    public void shouldBeAbleToCreateAPlaylist() {
        Playlist requestPlaylist = new Playlist().
                setName("New Playlist").
                setDescription("New playlist description").
                setPublic(false);

       Response response = PlaylistApi.post(requestPlaylist);
       assertThat(response.statusCode(), equalTo(201));

       // Deserialization
       Playlist responsePlaylist = response.as(Playlist.class);

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

        Response response = PlaylistApi.get("3bW3BNOhxJiAjtBhcsV7iF");
        assertThat(response.statusCode(), equalTo(200));

        Playlist responsePlaylist = response.as(Playlist.class);

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

        Response response = PlaylistApi.update("3bW3BNOhxJiAjtBhcsV7iF", requestPlaylist);
        assertThat(response.statusCode(), equalTo(200));
    }

    @Test
    public void shouldNotBeAbleToCreateAPlaylistWithoutName() {
        Playlist requestPlaylist = new Playlist().
                setName("").
                setDescription("New playlist description").
                setPublic(false);

        Response response = PlaylistApi.post(requestPlaylist);
        assertThat(response.statusCode(), equalTo(400));

        Error error = response.as(Error.class);

        assertThat(error.getError().getStatus(), equalTo(400));
        assertThat(error.getError().getMessage(), equalTo("Missing required field: name"));
    }

    @Test
    public void shouldNotBeAbleToCreateAPlaylistWithExpiredToken() {
        String invalid_token = "invalidAccessToken";
        Playlist requestPlaylist = new Playlist().
                setName("New Playlist").
                setDescription("New playlist description").
                setPublic(false);

        Response response = PlaylistApi.post(invalid_token, requestPlaylist);
        assertThat(response.statusCode(), equalTo(401));

        Error error = response.as(Error.class);

        assertThat(error.getError().getStatus(), equalTo(401));
        assertThat(error.getError().getMessage(), equalTo("Invalid access token"));
    }
}

package org.spotify.oauth2.api.applicationApi;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.spotify.oauth2.api.RestResource;
import org.spotify.oauth2.pojo.Playlist;
import org.spotify.oauth2.utils.ConfigLoader;

import static org.spotify.oauth2.api.Route.PLAYLISTS;
import static org.spotify.oauth2.api.Route.USERS;
import static org.spotify.oauth2.api.TokenManager.getToken;

public class PlaylistApi {

    @Step
    public static Response post(Playlist requestPlaylist) {
        return RestResource.post(USERS + "/" + ConfigLoader.getInstance().getUser()
                + PLAYLISTS, getToken(), requestPlaylist);
    }

    public static Response post(String token, Playlist requestPlaylist) {
        return RestResource.post(USERS + "/" + ConfigLoader.getInstance().getUser()
                + PLAYLISTS, token, requestPlaylist);
    }

    public static Response get(String playlistId) {
        return RestResource.get(PLAYLISTS + "/" + playlistId, getToken());
    }

    public static Response update(String playlistId, Playlist requestPlaylist) {
        return RestResource.update(PLAYLISTS + "/" + playlistId, getToken(), requestPlaylist);
    }
}

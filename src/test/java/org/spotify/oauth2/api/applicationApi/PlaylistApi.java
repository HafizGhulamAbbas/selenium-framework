package org.spotify.oauth2.api.applicationApi;

import io.restassured.response.Response;
import org.spotify.oauth2.api.RestResource;
import org.spotify.oauth2.pojo.Playlist;
import static org.spotify.oauth2.api.TokenManager.getToken;

public class PlaylistApi {

    public static Response post(Playlist requestPlaylist) {
        return RestResource.post("/users/3127ntzuaopscs44bj3jokvgmss4/playlists", getToken(), requestPlaylist);
    }

    public static Response post(String token, Playlist requestPlaylist) {
        return RestResource.post("/users/3127ntzuaopscs44bj3jokvgmss4/playlists", token, requestPlaylist);
    }

    public static Response get(String playlistId) {
        return RestResource.get("/playlists/" + playlistId, getToken());
    }

    public static Response update(String playlistId, Playlist requestPlaylist) {
        return RestResource.update("/playlists/" + playlistId, getToken(), requestPlaylist);
    }
}

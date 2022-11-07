package org.spotify.oauth2.api.applicationApi;

import io.restassured.response.Response;
import org.spotify.oauth2.api.RestResource;
import org.spotify.oauth2.pojo.Playlist;

import static io.restassured.RestAssured.given;
import static org.spotify.oauth2.api.SpecBuilder.getRequestSpec;
import static org.spotify.oauth2.api.SpecBuilder.getResponseSpec;

public class PlaylistApi {

    static String access_token = "BQCl5mf27hxok4ecH1v2IKxNEDRuGC-qoY9Zh5Ss8WtP_S-w-TTBubqZLQJk5GCvRqbMaP_zsJgzUXXC_UTnMfxt2tz2pfWldsALHFFZ3OZp4qIYF-8YkYXJlqbUbxvXAMR3e9hY-9VDBimByM6KRiq0NeG7I1TQfd_PUm0iL0MqZSW7j3ZUyvQZFM1qtWacLTXXpoERi81Y6v-ScSg6Wu6OZSv0vH59aL2UHGw2TZH_eIJbcoXbbWR-kd06F5ckzUxpl0XlCX-YEs7I";

    public static Response post(Playlist requestPlaylist) {
        return RestResource.post("/users/3127ntzuaopscs44bj3jokvgmss4/playlists", access_token, requestPlaylist);
    }

    public static Response post(String token, Playlist requestPlaylist) {
        return RestResource.post("/users/3127ntzuaopscs44bj3jokvgmss4/playlists", token, requestPlaylist);
    }

    public static Response get(String playlistId) {
        return RestResource.get("/playlists/" + playlistId, access_token);
    }

    public static Response update(String playlistId, Playlist requestPlaylist) {
        return RestResource.update("/playlists/" + playlistId, access_token, requestPlaylist);
    }
}

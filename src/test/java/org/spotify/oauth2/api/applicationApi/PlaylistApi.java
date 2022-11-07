package org.spotify.oauth2.api.applicationApi;

import io.restassured.response.Response;
import org.spotify.oauth2.pojo.Playlist;

import static io.restassured.RestAssured.given;
import static org.spotify.oauth2.api.SpecBuilder.getRequestSpec;
import static org.spotify.oauth2.api.SpecBuilder.getResponseSpec;

public class PlaylistApi {

    static String access_token = "BQBiF7RiMpTxTyKxnKXbbOzpcQnKfL-pPp2iuwtzAi4lqdYntC3ffx0W6cRQH0BJn2I5TSohJe5W6WZHxsIc64EbBrBqxCCAVMt16cwhWF8FqG7SeqSfbE5NCb7Suz2OmBiAeO6a-t2t63ub9j_ImA4qMyqwrgYaA-MV3eIeUah-ELESdt5kUPdlVIC8SmafvOegcvqbFiX4snb6pSP4qH2hWfWvCLQRyefdT-7kAaQILoFyqSZt-A_687r9mCZ3oif-OUlHp0Cp4cT_";

    public static Response post(Playlist requestPlaylist) {
        return given(getRequestSpec()).
                body(requestPlaylist).
                header("Authorization", "Bearer " + access_token).
        when().
                post("/users/3127ntzuaopscs44bj3jokvgmss4/playlists").
        then().spec(getResponseSpec()).
                extract().
                response();
    }

    public static Response post(String token, Playlist requestPlaylist) {
        return given(getRequestSpec()).
                body(requestPlaylist).
                header("Authorization", "Bearer " + token).
        when().
                post("/users/3127ntzuaopscs44bj3jokvgmss4/playlists").
        then().spec(getResponseSpec()).
                extract().
                response();
    }

    public static Response get(String playlistId) {
        return given(getRequestSpec()).
                header("Authorization", "Bearer " + access_token).
        when().
                get("/playlists/" + playlistId).
        then().spec(getResponseSpec()).
                extract().
                response();
    }

    public static Response update(String playlistId, Playlist requestPlaylist) {
        return given(getRequestSpec()).
                header("Authorization", "Bearer " + access_token).
                body(requestPlaylist).
        when().
                put("/playlists/" + playlistId).
        then().spec(getResponseSpec()).
                extract().
                response();
    }
}

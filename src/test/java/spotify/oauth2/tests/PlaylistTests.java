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
    String access_token = "BQCS3Rfa09Myp08WdOGL6HaHc-By5cTIN4t4n_uya9SeP2eamwsn4IihlPHVFW0qQZ-TIZ4mBVlUUVaeZ0wZgHe_khqt5bTqKHBwx_sA-cL7FSAngNkptprX_HHlCSKdgh64z1CIgX8QYTx_QsaPsvoOFXlKO3m_c6W4XyWlgGL6QnFZKXDVg6AUTOjeUePb1seXxzMOXm6A_uB2L5Nz06j8s9xeYSgTi3l9MaIYQMx4aJwTDvB4qTBtmxeQsPraXV9vQNC6NrX62zpE";

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
}

package org.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.rest.pojo.collection.*;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.ValueMatcher;
import org.skyscreamer.jsonassert.comparator.CustomComparator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;
import static io.restassured.RestAssured.given;


public class ComplexPojoTest {
    ResponseSpecification responseSpecification;

    @BeforeClass
    public void beforeClass(){
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().
                setBaseUri("https://api.postman.com").
                addHeader("x-api-key", "PMAK-634eeca9391e9e36398b8cbb-a73e5dfd984bf78ddadb401fe33e0d6773").
                setContentType(ContentType.JSON).
                log(LogDetail.ALL);
        RestAssured.requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).
                log(LogDetail.ALL);
        responseSpecification = responseSpecBuilder.build();
    }

    @Test
    public void complex_pojo_create_collection() throws JsonProcessingException, JSONException {
        Header header = new Header("Content-Type", "application/json");
        List<Header> headerList = new ArrayList<Header>();
        headerList.add(header);

        Body body = new Body("raw", "{\"data\": \"123\"}");

        Request request = new Request("https://postman-echo.com/post", "POST",
                "This is a sample POST Request", headerList, body);

        RequestRoot requestRoot = new RequestRoot("Sample POST Request", request);
        List<RequestRoot> requestRootList = new ArrayList<RequestRoot>();
        requestRootList.add(requestRoot);

        Folder folder = new Folder("This is a folder", requestRootList);
        List<Folder> folderList = new ArrayList<Folder>();
        folderList.add(folder);

        Info info = new Info("Sample Collection1", "This is just a sample collection.",
                "https://schema.getpostman.com/json/collection/v2.1.0/collection.json");

        Collection collection = new Collection(info, folderList);
        CollectionRoot collectionRoot = new CollectionRoot(collection);

        String collectionUid = given().
                body(collectionRoot).
        when().
                post("/collections").
        then().spec(responseSpecification).
                extract().
                response().path("collection.uid");

        CollectionRoot deserializedCollectionRoot = given().
                pathParam("collectionUid", collectionUid).
        when().
                get("/collections/{collectionUid}").
        then().spec(responseSpecification).
                extract().
                response().
                as(CollectionRoot.class);

        ObjectMapper objectMapper = new ObjectMapper();
        String collectionRootStr = objectMapper.writeValueAsString(collectionRoot);
        String deserializedCollectionRootStr = objectMapper.writeValueAsString(deserializedCollectionRoot);

        JSONAssert.assertEquals(collectionRootStr, deserializedCollectionRootStr,
                new CustomComparator(JSONCompareMode.STRICT,
                        new Customization("collection.item[*].item[*].request.url", new ValueMatcher<Object>() {
                            public boolean equal(Object o1, Object o2) {
                                return true;
                            }
                        })
                )
        );
    }
}

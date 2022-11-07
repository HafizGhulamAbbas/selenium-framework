package org.spotify.oauth2.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilder {

    static String access_token = "BQBiF7RiMpTxTyKxnKXbbOzpcQnKfL-pPp2iuwtzAi4lqdYntC3ffx0W6cRQH0BJn2I5TSohJe5W6WZHxsIc64EbBrBqxCCAVMt16cwhWF8FqG7SeqSfbE5NCb7Suz2OmBiAeO6a-t2t63ub9j_ImA4qMyqwrgYaA-MV3eIeUah-ELESdt5kUPdlVIC8SmafvOegcvqbFiX4snb6pSP4qH2hWfWvCLQRyefdT-7kAaQILoFyqSZt-A_687r9mCZ3oif-OUlHp0Cp4cT_";

    public static RequestSpecification getRequestSpec() {
        return new RequestSpecBuilder().
                setBaseUri("https://api.spotify.com").
                setBasePath("/v1").
                addHeader("Authorization", "Bearer " + access_token).
                setContentType(ContentType.JSON).
                log(LogDetail.ALL).
                build();
    }

    public static ResponseSpecification getResponseSpec() {
        return new ResponseSpecBuilder().
                log(LogDetail.ALL).
                build();
    }
}

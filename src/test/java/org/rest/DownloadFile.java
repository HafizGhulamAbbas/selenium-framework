package org.rest;

import org.testng.annotations.Test;

import java.io.*;

import static io.restassured.RestAssured.given;

public class DownloadFile {

    @Test
    public void download_file() throws IOException {
        // https://github.com/appium-boneyard/sample-code/blob/master/sample-code/apps/ApiDemos/bin/ApiDemos-debug.apk

        // Method 1
        byte[] bytes = given().
                baseUri("https://github.com").
                log().all().
        when().
                get("/appium-boneyard/sample-code/raw/master/sample-code/apps/ApiDemos/bin/ApiDemos-debug.apk").
        then().
                log().all().
                extract().
                response().asByteArray();

        OutputStream os = new FileOutputStream(new File("ApiDemos-debug.apk"));
        os.write(bytes);
        os.close();

        // Method 2
        /*InputStream is = given().
                baseUri("https://github.com").
                log().all().
        when().
                get("/appium-boneyard/sample-code/raw/master/sample-code/apps/ApiDemos/bin/ApiDemos-debug.apk").
        then().
                log().all().
                extract().
                response().asInputStream();

        OutputStream os = new FileOutputStream(new File("ApiDemos-debug.apk"));
        byte[] bytes = new byte[is.available()];
        is.read(bytes);
        os.write(bytes);
        os.close();*/
    }
}

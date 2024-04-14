package com.academy.api;

import com.academy.api.runners.BaseRunner;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

import java.io.InputStream;

public class FirstTestApi extends BaseRunner {

    @Test
    public void check() {
        RestAssured.get("/api/cities")
                .then()
                .statusCode(200);
//        InputStream stream = RestAssured.get("/api/cities").asInputStream();
//        byte[] byteArr = RestAssured.get("/api/cities").asByteArray();
        System.out.println(RestAssured.get("/api/cities").asString());
    }
}

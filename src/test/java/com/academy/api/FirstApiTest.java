package com.academy.api;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FirstApiTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "http://speak-ukrainian.eastus2.cloudapp.azure.com/dev/";
    }

    @Test
    public void test() {
        RestAssured.get("/api/about");
    }
}

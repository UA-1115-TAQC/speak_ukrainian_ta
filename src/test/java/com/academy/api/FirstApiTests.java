package com.academy.api;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FirstApiTests {

    @BeforeClass
    public static void setUp(){
        RestAssured.baseURI = "http://speak-ukrainian.eastus2.cloudapp.azure.com/dev/";

    }


}

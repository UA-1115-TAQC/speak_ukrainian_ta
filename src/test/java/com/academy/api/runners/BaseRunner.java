package com.academy.api.runners;

import com.academy.ui.runners.utils.ConfigProperties;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class BaseRunner {
    protected static ConfigProperties configProperties;

    @BeforeClass
    public void setUp() {
        configProperties = new ConfigProperties();
        RestAssured.baseURI = configProperties.getBaseUrl();
    }
}

package com.academy.api.clubsPage;

import com.academy.api.runners.BaseAPITestRunner;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.lessThan;


public class ClubsResponseTimeTest extends BaseAPITestRunner {

    @Test(description = "Verify response time of API clubs page")
    public void testClubsResponseTime() {

        long EXPECTED_RESPONSE_TIME = 2000;

        RestAssured.get("/api/clubs")
                .then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(EXPECTED_RESPONSE_TIME));
    }

}

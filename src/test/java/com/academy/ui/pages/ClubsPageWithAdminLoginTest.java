package com.academy.ui.pages;

import com.academy.ui.components.header.HeaderComponent;
import com.academy.ui.runners.LoginWithAdminTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.sql.*;
import java.time.Duration;
import java.util.List;
import java.util.Objects;

public class ClubsPageWithAdminLoginTest extends LoginWithAdminTestRunner {

    private SoftAssert softAssert;

    @BeforeMethod
    public void clubPageSetUp() {
        softAssert = new SoftAssert();
    }

    @Test
    @Description("Verify that the system shows online clubs when the user chooses 'without location'")
    @Issue("TUA-880")
    public void checkClubsPageShowsOnlineClubWithoutLocation() {
        HeaderComponent headerComponent = homePage.header.clickCityLocation();
        ClubsPage clubsPage = headerComponent.selectClubsCityLocation("Без локації");
        softAssert.assertTrue(driver.getCurrentUrl().contains("clubs"));

        List<ClubCardComponent> clubCards = clubsPage.getClubCards();
        long actualQuantityOfOnlineClubs = clubCards.stream()
                .filter(club -> club.getClubOnline().getText().equals("Гурток онлайн"))
                .count();
        softAssert.assertEquals(clubCards.size(), actualQuantityOfOnlineClubs);

        softAssert.assertAll();
    }

    public String[] getClubsByLocationAndCategory(String location, String category) throws SQLException {
        String url = configProperties.getSQLUrl();
        try(Connection connection = DriverManager.getConnection(url, configProperties.getSQLUser(), configProperties.getSQLPassword());
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {

            String querySelector = "SELECT DISTINCT c.name\n" +
                    "FROM clubs c\n" +
                    "JOIN locations AS l ON c.id = l.club_id\n" +
                    "JOIN cities AS ct ON l.city_id = ct.id\n" +
                    "JOIN club_category AS cc ON c.id = cc.club_id\n" +
                    "JOIN categories AS cat ON cc.category_id = cat.id\n" +
                    "WHERE ct.name = '" + location + "' AND cat.name = '" + category + "'" +
                    "LIMIT 8";

            ResultSet resultSet = statement.executeQuery(querySelector);

            resultSet.last();
            int rowCount = resultSet.getRow();
            resultSet.beforeFirst();
            String[] clubs = new String[rowCount];

            int index = 0;
            while (resultSet.next()) {
                clubs[index] = resultSet.getString("name");
                index++;
            }

            return clubs;
        }
    }

    @Test
    @Issue("TUA-851")
    public void checkSearchClubsConductedSelectedCity() throws SQLException {
        HeaderComponent headerComponent = homePage.header.clickCityLocation();
        ClubsPage clubsPage = headerComponent.selectClubsCityLocation("Київ");
        softAssert.assertTrue(headerComponent.getSelectedCity().getText().contains("Київ"), "Not in Київ");
        clubsPage.setTextHeaderSearch("Спортивні секції");
        clubsPage.getClubCards().forEach(item -> softAssert.assertTrue(item.getAddress().getText().contains("Київ"), "Not in that location"));

        String []kyivClubs = new String[clubsPage.getClubCards().size()];
        for (int i = 0; i < clubsPage.getClubCards().size(); i++) {
            kyivClubs[i] = clubsPage.getClubCards().get(i).getClubName();
        }

        clubsPage = headerComponent.selectClubsCityLocation("Харків");
        softAssert.assertTrue(headerComponent.getSelectedCity().getText().contains("Харків"), "Not in Харків");
        clubsPage.getClubCards().forEach(item -> softAssert.assertTrue(item.getAddress().getText().contains("Харків"), "Not in that location"));

        String []kharkivClubs = new String[clubsPage.getClubCards().size()];
        for (int i = 0; i < clubsPage.getClubCards().size(); i++) {
            kharkivClubs[i] = clubsPage.getClubCards().get(i).getClubName();
        }

        String[] kyivClubsSQL = getClubsByLocationAndCategory("Київ", "Спортивні секції");
        String[] kharkivClubsSQL = getClubsByLocationAndCategory("Харків", "Спортивні секції");

        for(int i = 0; i < kyivClubsSQL.length; ++i) {
            String sanitizedSQL = kyivClubsSQL[i].replaceAll("\\s+", "");
            String sanitizedQLS = kyivClubs[i].replaceAll("\\s+", "");
            softAssert.assertTrue(sanitizedSQL.equalsIgnoreCase(sanitizedQLS), "Clubs in Kyiv are not in the right order");
        }

        for(int i = 0; i < kharkivClubsSQL.length; ++i) {
            String sanitizedSQL = kharkivClubsSQL[i].replaceAll("\\s+", "");
            String sanitizedQLS = kharkivClubs[i].replaceAll("\\s+", "");
            softAssert.assertTrue(sanitizedSQL.equalsIgnoreCase(sanitizedQLS), "Clubs in Kharkiv are not in the right order");
        }

        softAssert.assertAll();
    }
}

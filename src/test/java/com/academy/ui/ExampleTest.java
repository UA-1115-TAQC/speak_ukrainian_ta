package com.academy.ui;

import com.academy.ui.pages.ClubCardComponent;
import com.academy.ui.pages.ClubsPage;
import com.academy.ui.pages.HomePage;
import com.academy.ui.runners.BaseTestRunner;
import org.testng.annotations.Test;

public class ExampleTest extends BaseTestRunner {
    @Test
    public void open_menu_test() {

        driver.get("http://speak-ukrainian.eastus2.cloudapp.azure.com/dev/clubs");
        ClubsPage page = new ClubsPage(driver);
        ClubCardComponent card = page.getClubCards().get(1);
        String s = card.getTitle().getText();
        System.out.println("test");


    }
}

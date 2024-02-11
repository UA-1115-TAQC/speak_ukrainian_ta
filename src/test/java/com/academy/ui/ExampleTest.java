package com.academy.ui;

import com.academy.ui.pages.HomePage;
import com.academy.ui.runners.BaseTestRunner;
import org.testng.annotations.Test;

public class ExampleTest extends BaseTestRunner {
    @Test
    public void open_menu_test() {
        HomePage home = new HomePage(driver);
        //home.header.clickMenu();
        System.out.println(home.header.toAllNewsPage().getClubCardLineComponent()
                .getClubTitle().getText().contains("Школа перкусії"));
    }
}

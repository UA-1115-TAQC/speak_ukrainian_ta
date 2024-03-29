package com.academy.ui.addClub;

import org.testng.annotations.DataProvider;

public class AddClubWithManagerDataProvider {

    @DataProvider(name = "validDescription")
    private Object[][] descriptionValidInput(){
        return new String[][]{
                {"Lorem ipsum dolor sit amet consectetur efficitur"},
                {"123 Lorem ipsum dolor 456 sit amet consectetur 789"},
                {"!\\\"Lorem!#$%&'()*+ipsum,-./:;<=>?@dolor[]^_`{}~"},
        };
    }

    @DataProvider(name = "invalidAddress")
    private Object[][] addressInvalidInput(){
        return new String[][]{
                {"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean ma"},
                {"абвг"},
                {"абвгдъ"},
                {"абвгдё"},
                {"абвгдэ"},
                {"абвгды"},
        };
    }
}

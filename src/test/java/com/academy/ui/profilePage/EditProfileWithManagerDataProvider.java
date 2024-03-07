package com.academy.ui.profilePage;

import org.testng.annotations.DataProvider;

public class EditProfileWithManagerDataProvider {

    @DataProvider(name = "invalidLastNameInput")
    private Object[][] invalidLastNameInput(){
        return new String[][]{
                {"abcdefghijklmnopqrstuvwxyz","Прізвище не може містити більше, ніж 25 символів"},
                {"#$%^&","Прізвище не може містити спеціальні символи"},
                {"12345","Прізвище не може містити цифри"},
                {"-Lastname","Прізвище повинно починатися і закінчуватися літерою"},
                {"Lastname-","Прізвище повинно починатися і закінчуватися літерою"},
                {" Lastname","Прізвище повинно починатися і закінчуватися літерою"},
                {"Lastname ","Прізвище повинно починатися і закінчуватися літерою"},
                {"'Lastname","Прізвище повинно починатися і закінчуватися літерою"},
                {"Lastname'","Прізвище повинно починатися і закінчуватися літерою"},
                {"","Будь ласка введіть Ваше прізвище"}
        };
    }
}

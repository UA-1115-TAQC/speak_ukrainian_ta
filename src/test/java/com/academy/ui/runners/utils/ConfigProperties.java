package com.academy.ui.runners.utils;

import io.qameta.allure.Step;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigProperties {
    private static final String imgFolderPath = "src/test/resources/images/";
    private final Properties properties;

    public ConfigProperties() {
        properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream("src/test/resources/config.properties");
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Step("Get absolute path of image {imageName}")
    public static String getImagePath(String imageName) {
        String path = null;
        try {
            File img = new File(imgFolderPath + imageName);
            path = img.getAbsolutePath();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return path;
    }

    @Step("Get base URL from config properties file")
    public String getBaseUrl() {
        return properties.getProperty("base.url");
    }

    @Step("Get admin email from config properties file")
    public String getAdminEmail() {
        return properties.getProperty("admin.email");
    }

    @Step("Get admin password from config properties file")
    public String getAdminPassword() {
        return properties.getProperty("admin.pass");
    }

    @Step("Get user email from config properties file")
    public String getUserEmail() {
        return properties.getProperty("user.email");
    }

    @Step("Get user password from config properties file")
    public String getUserPassword() {
        return properties.getProperty("user.pass");
    }

    @Step("Get manager email from config properties file")
    public String getManagerEmail() {
        return properties.getProperty("manager.email");
    }

    @Step("Get manager password from config properties file")
    public String getManagerPassword() {
        return properties.getProperty("manager.pass");
    }

    @Step("Set manager password : {pass}")
    public void setManagerPassword(String pass) {
        properties.setProperty("manager.pass ", pass);
    }

    @Step("Get user first name from config properties file")
    public String getUserFirstname() {
        return properties.getProperty("user.firstname");
    }

    @Step("Get user last name from config properties file")
    public String getUserLastname() {
        return properties.getProperty("user.lastname");
    }

    @Step("Get user phone from config properties file")
    public String getUserPhone() {
        return properties.getProperty("user.phone");
    }
}

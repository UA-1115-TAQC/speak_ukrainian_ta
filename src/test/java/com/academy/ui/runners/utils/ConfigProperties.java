package com.academy.ui.runners.utils;

import io.qameta.allure.Step;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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


    public String getBaseUrl() {
        return properties.getProperty("base.url");
    }


    public String getAdminEmail() {
        return properties.getProperty("admin.email");
    }


    public String getAdminPassword() {
        return properties.getProperty("admin.pass");
    }


    public String getUserEmail() {
        return properties.getProperty("user.email");
    }


    public String getUserPassword() {
        return properties.getProperty("user.pass");
    }


    public String getManagerEmail() {
        return properties.getProperty("manager.email");
    }


    public String getManagerPassword() {
        return properties.getProperty("manager.pass");
    }


    public void setManagerPassword(String pass) {
        properties.setProperty("manager.pass", pass);
        try (FileOutputStream outputStream = new FileOutputStream("src/test/resources/config.properties")) {
            properties.store(outputStream, "Updated manager password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String getUserFirstname() {
        return properties.getProperty("user.firstname");
    }


    public String getUserLastname() {
        return properties.getProperty("user.lastname");
    }


    public String getUserPhone() {
        return properties.getProperty("user.phone");
    }
}

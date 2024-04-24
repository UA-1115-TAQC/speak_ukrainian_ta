package com.academy.ui.runners.utils;

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

    public String getUserRole() {
        return properties.getProperty("user.role.name");
    }

    public String getUserLogo() {
        return properties.getProperty("user.url.logo");
    }

    public String getUserStatus() {
        return properties.getProperty("user.status");
    }

    public long getUserId() {
        String userId =  properties.getProperty("user.id");
        return Long.parseLong(userId);
    }

    public String getManagerFirstname() {
        return properties.getProperty("manager.firstname");
    }


    public String getManagerLastname() {
        return properties.getProperty("manager.lastname");
    }


    public String getManagerPhone() {
        return properties.getProperty("manager.phone");
    }

    public String getManagerRole() {
        return properties.getProperty("manager.role.name");
    }

    public String getManagerLogo() {
        return properties.getProperty("manager.url.logo");
    }

    public String getManagerStatus() {
        return properties.getProperty("manager.status");
    }

    public int getManagerId() {
        String managerId =  properties.getProperty("manager.id");
        return Integer.parseInt(managerId);
    }

}

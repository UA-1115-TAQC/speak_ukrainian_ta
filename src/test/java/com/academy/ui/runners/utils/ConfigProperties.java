package com.academy.ui.runners.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigProperties {
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
    public String getUserFirstname(){return properties.getProperty("user.firstname");}
    public String getUserLastname(){return properties.getProperty("user.lastname");}
    public String getUserPhone(){return properties.getProperty("user.phone");}
<<<<<<< HEAD
    public void setManagerPassword(String pass){
        properties.setProperty("manager.pass ", pass);
    }
=======
>>>>>>> 659ff91ef23708e4c5b885aacceb12dc9e612300
}
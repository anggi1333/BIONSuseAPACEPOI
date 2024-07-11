package com.example;

import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RunBIONS {
    public static void main(String[] args) {
        // Disable verbose logging for Selenium
        Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);

        // Set ChromeDriver properties
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
        System.setProperty("webdriver.chrome.silentOutput", "true");

        // Chrome options setup
        ChromeOptions options = new ChromeOptions();
        options.setBinary("D:\\chrome-win\\chrome.exe");
        options.addArguments("--disable-notifications");

        // Initialize WebDriver
        WebDriver driver = new ChromeDriver(options);
        try {

            // Navigate to the login page
            driver.get("https://appdev.bions.id/login");

            // Perform login
            Login.log(driver);

            // Perform stock buying actions
            StockBuy.Buy(driver);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}

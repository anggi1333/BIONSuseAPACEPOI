package com.example;

import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

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
            // Excel reader setup
            ExcelReader excelReader = new ExcelReader("D:/Tech/login.xlsx");
            Iterator<Row> rowIterator = excelReader.getRowIterator();

            // Navigate to the login page
            driver.get("https://appdev.bions.id/login");
            driver.manage().window().maximize();

            // Perform login
            Login.testScenariologin(driver, "DEV", "reni666", "d", "q12345");

            // Perform stock buying actions
            StockBuy.Buy(driver, rowIterator, excelReader);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}

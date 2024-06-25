package com.example;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class StockBuy {

    public static void Buy(WebDriver driver, Iterator<Row> rowIterator, ExcelReader excelReader) {
        // Skip the header row
        if (rowIterator.hasNext()) {
            rowIterator.next();
        }

        int rowIndex = 1; // Assuming data starts from the first row (excluding header)
        int passCount = 0;
        int failCount = 0;
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            String StockName = getCellStringValue(row, 0);
            String StockPrice = getCellStringValue(row, 1);
            String StockLot = getCellStringValue(row, 2);

            if (StockName == null || StockPrice == null || StockLot == null) {
                rowIndex++;
                continue;
            }

            WebDriverWait wait = new WebDriverWait(driver, 4);
            WebElement portp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div/div/div/div[1]/div/div[1]/div[1]/div/div/div[2]/div[2]/div/div/div/div/div/div/div[1]/div[1]/div/div/div/div/div/div/div/div[2]/div[2]/div/div/div/div[1]/div/div[2]/div[2]/div/div/div[2]")));
            portp.click();
            try {
                // Wait for 1 seconds
                Thread.sleep(1000);
            } catch (InterruptedException f) {
                f.printStackTrace();
                Thread.currentThread().interrupt();
            }
            WebElement menu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div/div/div/div[1]/div/div[1]/div[1]/div/div/div[2]/div[2]/div/div/div/div/div/div/div[1]/div[1]/div/div/div/div/div/div/div/div[2]/div[2]/div/div/div/div[1]/div/div[2]/div[2]/div/div/div[4]")));
            menu.click();
            menu.click();
            try {
                // Wait for 1 second
                Thread.sleep(1000);
            } catch (InterruptedException f) {
                f.printStackTrace();
                Thread.currentThread().interrupt();
            }
            WebElement buy = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Buy']")));
            Actions actions = new Actions(driver);
            actions.moveToElement(buy).click().perform();
            String input = "ACES,ADRO,AKRA,AMRT,ANTM,ARTO,ASII,BBCA,BBNI,BBRI,BBTN,BMRI,BRIS,BRPT,BUKA,CPIN,EMTK,ESSA,EXCL,GGRM,GOTO,HRUM,ICBP,INCO,INDF,INKP,INTP,ITMG,KLBF,MAPI,MBMA,MDKA,MEDC,MTEL,PGAS,PGEO,PTBA,PTMP,SIDO,SMGR,SRTG,TLKM,TOWR,UNTR,UNVR";
            String[] words = input.split(",");
            List<String> wordList = Arrays.asList(words);
            Collections.shuffle(wordList);
            StringBuilder selectedLetters = new StringBuilder();
            for (int i = 0; i < 4; i++) {
                String word = wordList.get(i);
                selectedLetters.append(word, 0, Math.min(word.length(), 4));
            }
            String finalOutput = selectedLetters.substring(0, Math.min(selectedLetters.length(), 4));

            WebElement stockbuy = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Stock']/following-sibling::*/div/div/div/div/input")));
            stockbuy.sendKeys(Keys.CONTROL + "a");

            try {
                // Wait for 0.5 seconds
                Thread.sleep(500);
            } catch (InterruptedException f) {
                f.printStackTrace();
                Thread.currentThread().interrupt();
            }
            stockbuy.sendKeys(Keys.BACK_SPACE);
            if (StockName.toLowerCase().contains("random")) {
                stockbuy.sendKeys(finalOutput);
            } else {
                stockbuy.sendKeys(StockName);
            }
            stockbuy.sendKeys(Keys.ENTER);

            List<WebElement> errorElements = driver.findElements(By.xpath("//*[text()='Error']"));
            if (!errorElements.isEmpty()) {
                WebElement error = errorElements.get(0);
                String txterror = error.getText();
                WebElement failElements = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Error'][1]/following-sibling::*[1]")));
                String txtfail = failElements.getText();
                String actualResult = "FAIL " + StockName + "-----------" + txterror + " " + txtfail;
                excelReader.updateStatus(rowIndex, "FAIL");
                excelReader.updateResult(rowIndex, actualResult);
                failCount++;
                System.out.println("FAIL STOCK " + StockName + " or " + finalOutput + "-----------" + txterror + " " + txtfail);
                WebElement okElements = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Error'][1]/following-sibling::*[2]/div")));
                okElements.click();
                rowIndex++;
                continue;
            }
            WebElement lotbuy = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Lot']/following-sibling::*/div[2]/div/input")));
            lotbuy.sendKeys(Keys.CONTROL + "a");
            try {
                // Wait for 0.5 seconds
                Thread.sleep(500);
            } catch (InterruptedException f) {
                f.printStackTrace();
                Thread.currentThread().interrupt();
            }
            lotbuy.sendKeys(Keys.BACK_SPACE);
            lotbuy.sendKeys(StockLot);

            try {
                // Wait for 2 seconds
                Thread.sleep(1000);
            } catch (InterruptedException f) {
                f.printStackTrace();
                Thread.currentThread().interrupt();
            }

            WebElement buybtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'BUY') and contains(@style, 'text-align: center')]")));
            buybtn.click();
            WebElement sendthisorderyes = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@style, 'font-weight: normal;') and text()='OK']")));
            sendthisorderyes.click();
            WebElement ordersendyes = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@style, 'font-weight: normal;') and text()='OK']")));
            ordersendyes.click();

            try {
                // Wait for 1 second
                Thread.sleep(1000);
            } catch (InterruptedException f) {
                f.printStackTrace();
                Thread.currentThread().interrupt();
            }

            WebElement statusElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-testid='orderstatus-0']")));
            String text = statusElement.getText();
            WebElement stocklot = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-testid='orderlot-0']")));
            String stocklottxt = stocklot.getText();
            WebElement orderprice1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-testid='orderprice-0']")));
            String orderpricetxt = orderprice1.getText();

            if (text.contains("REJECTED")) {
                WebElement rejects = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-testid='orderreject-0']")));
                String rejectstatus = rejects.getText();
                try {
                    // Wait for 2 seconds
                    Thread.sleep(1000);
                } catch (InterruptedException f) {
                    f.printStackTrace();
                    Thread.currentThread().interrupt();
                }
                // Handle rejection scenario
                // Update Excel with rejection status
                String actualResult = "REJECTED " + StockName + " - " + rejectstatus;
                excelReader.updateStatus(rowIndex, "FAIL");
                excelReader.updateResult(rowIndex, actualResult);
                failCount++;
                System.out.println("REJECTED " + StockName + " - " + rejectstatus);
                rowIndex++;
                continue;
            }

            // Continue with other validations or actions
            // For example, validate stock name result, status, lot size, order price
            // Update Excel with PASS status if applicable
            String actualResult = "PASS " + StockName+" "+ statusElement;
            excelReader.updateResult(rowIndex, actualResult);
            excelReader.updateStatus(rowIndex, "PASS");
            passCount++;
            System.out.println("PASS " + StockName+" "+ statusElement);

            rowIndex++;
        }

        // Add summary row to Excel sheet
        excelReader.addSummaryRow(passCount, failCount);
    }

    private static String getCellStringValue(Row row, int cellIndex) {
        Cell cell = row.getCell(cellIndex);
        if (cell == null) {
            return null;
        }

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue()).trim();
            default:
                return null;
        }
    }
}
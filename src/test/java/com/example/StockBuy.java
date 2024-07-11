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

    public static void Buy(WebDriver driver) {
        ExcelReader excelReader = new ExcelReader("D:/Tech/BIONS_Automate.xlsx", "SekenarioBUY");
        Iterator<Row> rowIterator = excelReader.getRowIterator();

        // Skip header row if it exists
        if (rowIterator.hasNext()) {
            rowIterator.next();
        }

        int rowIndex = 2; // Assuming data starts from the 2nd row (excluding header)
        int passCount = 0;
        int failCount = 0;

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            String StockName = getCellStringValue(row, 0);
            String pricingmethod = getCellStringValue(row, 1);
            Double StockLot = null;
            try {
                StockLot = row.getCell(3).getNumericCellValue();
            } catch (NullPointerException | IllegalStateException e) {
                // Handle the exception appropriately
            }

            if (StockName == null ||  StockLot == null || pricingmethod == null) {
                rowIndex++;
                continue; // Skip processing if any essential data is missing
            }

            WebDriverWait wait = new WebDriverWait(driver, 10);

            // Click on the appropriate elements to initiate the buy process
            try {
                WebElement portp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div/div/div/div[1]/div/div[1]/div[1]/div/div/div[2]/div[2]/div/div/div/div/div/div/div[1]/div[1]/div/div/div/div/div/div/div/div[2]/div[2]/div/div/div/div[1]/div/div[2]/div[2]/div/div/div[2]")));
                portp.click();

                // Wait briefly
                Thread.sleep(1000);

                WebElement menu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div/div/div/div[1]/div/div[1]/div[1]/div/div/div[2]/div[2]/div/div/div/div/div/div/div[1]/div[1]/div/div/div/div/div/div/div/div[2]/div[2]/div/div/div/div[1]/div/div[2]/div[2]/div/div/div[4]")));
                menu.click();
                menu.click();

                // Wait briefly
                Thread.sleep(1000);

                WebElement buy = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div/div/div/div[1]/div/div[1]/div[1]/div/div/div[2]/div[2]/div/div/div/div/div/div/div[1]/div[1]/div/div/div/div/div/div/div/div[2]/div[2]/div/div/div/div[1]/div/div[1]/div[4]/div/div/div/div[2]/div[2]/div/div/div/div[1]/div/div[2]/div[1]/div/div/div/div/div[21]/div/div/div/div[2]")));
                Actions actions = new Actions(driver);
                actions.moveToElement(buy).click().perform();

                // Generate random stock selection
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
                Thread.sleep(500);
                stockbuy.sendKeys(Keys.BACK_SPACE);

                if (StockName.toLowerCase().contains("random")) {
                    stockbuy.sendKeys(finalOutput);
                } else {
                    stockbuy.sendKeys(StockName);
                }
                stockbuy.sendKeys(Keys.ENTER);
                Thread.sleep(1000);

                // Check for error
                List<WebElement> errorElements = driver.findElements(By.xpath("//*[text()='Error']"));
                if (!errorElements.isEmpty()) {
                    WebElement error = errorElements.get(0);
                    String txterror = error.getText();
                    WebElement failElements = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Error'][1]/following-sibling::*[1]")));
                    String txtfail = failElements.getText();
                    String actualResult = "FAIL " + StockName + " - " + txterror + " " + txtfail;
                    excelReader.updateStatus(rowIndex, "FAIL");
                    excelReader.updateResult(rowIndex, actualResult);
                    failCount++;
                    System.out.println("FAIL STOCK " + StockName + " - " + txterror + " " + txtfail);
                    WebElement okElements = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Error'][1]/following-sibling::*[2]/div")));
                    okElements.click();
                    rowIndex++;
                    continue;
                }


                By priceXpath = By.xpath("//*[text()='High']/following-sibling::*");
                By arrowButtonAXpath = By.xpath("//*[text()='Main Board']/parent::*/following-sibling::*");
                By arrowButtonBXpath = By.xpath("//*[text()='Watch List Board']/parent::*/following-sibling::*");
                By arrowButtonCXpath = By.xpath("//*[text()='Development Board']/parent::*/following-sibling::*");
                By arrowButtonDXpath = By.xpath("/html/body/div[1]/div/div/div/div/div[1]/div/div[1]/div[1]/div/div/div[2]/div[2]/div/div/div/div/div/div/div[1]/div[1]/div/div/div/div/div/div/div/div[2]/div[2]/div/div/div/div[1]/div/div[1]/div[3]/div/div/div[2]/div[2]/div[2]/div/div/div/div[1]/div/div[2]/div/div/div[1]/div/div/div/div[3]/div/div[2]");
                WebElement priceElement = null;
                try {
                    priceElement = driver.findElement(priceXpath);
                } catch (org.openqa.selenium.NoSuchElementException e) {
                }

                if (priceElement != null && priceElement.isDisplayed()) {

                } else {

                    WebElement arrowButtonA = null;
                    try {

                        arrowButtonA = driver.findElement(arrowButtonAXpath);
                    } catch (org.openqa.selenium.NoSuchElementException e) {
                    }

                    WebElement arrowButtonB = null;
                    Thread.sleep(100);
                    try {
                        arrowButtonB = driver.findElement(arrowButtonBXpath);
                        Thread.sleep(100);
                    } catch (org.openqa.selenium.NoSuchElementException e) {
                    }

                    WebElement arrowButtonC = null;
                    Thread.sleep(100);
                    try {
                        arrowButtonC = driver.findElement(arrowButtonCXpath);
                        Thread.sleep(100);
                    } catch (org.openqa.selenium.NoSuchElementException e) {
                    }  Thread.sleep(100);
                    WebElement arrowButtonD = null;
                    try {
                        Thread.sleep(100);
                        arrowButtonD = driver.findElement(arrowButtonDXpath);
                    } catch (org.openqa.selenium.NoSuchElementException e) {
                    }  Thread.sleep(100);

                    boolean clicked = false;

                    if (arrowButtonA != null && arrowButtonA.isDisplayed()) {
                        try {  Thread.sleep(100);
                            actions.moveToElement(arrowButtonA).click().perform();
                            clicked = true;
                            Thread.sleep(100);
                        } catch (Exception e) {
                        }
                        Thread.sleep(100);
                    } else if (arrowButtonB != null && arrowButtonB.isDisplayed()) {
                        Thread.sleep(100);
                        try {
                            Thread.sleep(100);
                            actions.moveToElement(arrowButtonB).click().perform();
                            clicked = true;
                            Thread.sleep(100);
                        } catch (Exception e) {
                        }
                    } else if (arrowButtonC != null && arrowButtonC.isDisplayed()) {
                        try {
                            Thread.sleep(100);
                            actions.moveToElement(arrowButtonC).click().perform();
                            clicked = true;
                            Thread.sleep(100);
                        } catch (Exception e) {
                            Thread.sleep(100);
                        }
                    } else if (arrowButtonD != null && arrowButtonD.isDisplayed()) {
                        try {
                            Thread.sleep(100);
                            actions.moveToElement(arrowButtonD).click().perform();
                            clicked = true;
                            Thread.sleep(100);
                        } catch (Exception e) {
                        }
                    }
                    if (!clicked) {
                        Thread.sleep(100);
                    }
                }

                WebElement open = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Open']/following-sibling::*")));
                String opentxt = open.getText();
                WebElement average = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Average']/following-sibling::*")));
                String averagetxt = average.getText();
                WebElement high = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='High']/following-sibling::*")));
                String hightxt = high.getText();
                WebElement llow = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='L.Low']/following-sibling::*")));
                String llowtxt = llow.getText();
                WebElement low = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Low']/following-sibling::*")));
                String lowtxt = low.getText();
                WebElement pricebuy = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Price']/following-sibling::*/div[2]/div/input")));
                pricebuy.sendKeys(Keys.CONTROL + "a");
                pricebuy.sendKeys(Keys.BACK_SPACE);
                try {
                    // Wait for 0.5 seconds
                    Thread.sleep(500);
                } catch (InterruptedException f) {
                    f.printStackTrace();
                    Thread.currentThread().interrupt();
                }


                switch (pricingmethod) {
                    case "high":
                        pricebuy.sendKeys(hightxt.equalsIgnoreCase("0") ? llowtxt : hightxt);
                        break;
                    case "low":
                        pricebuy.sendKeys(lowtxt.equalsIgnoreCase("0") ? llowtxt : lowtxt);
                        break;
                    case "open":
                        pricebuy.sendKeys(opentxt.equalsIgnoreCase("0") ? llowtxt : opentxt);
                        break;
                    case "average":
                        pricebuy.sendKeys(averagetxt.equalsIgnoreCase("0") ? llowtxt : averagetxt);
                        break;
                    default:
                        Double StockPrice = row.getCell(2).getNumericCellValue();
                        pricebuy.sendKeys(String.valueOf(StockPrice.longValue()));
                        break;
                }



                // Enter Lot and complete buy process
                WebElement lotbuy = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Lot']/following-sibling::*/div[2]/div/input")));
                lotbuy.sendKeys(Keys.CONTROL + "a");
                Thread.sleep(500);
                lotbuy.sendKeys(Keys.BACK_SPACE);
                lotbuy.sendKeys(String.valueOf(StockLot.longValue()));

                Thread.sleep(1000);

                WebElement buybtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'BUY') and contains(@style, 'text-align: center')]")));
                buybtn.click();
                Thread.sleep(1000);
                List<WebElement> errorElements2 = driver.findElements(By.xpath("//*[text()='Error']"));
                if (!errorElements2.isEmpty()) {
                    WebElement error = errorElements2.get(0);
                    String txterror = error.getText();
                    WebElement failElements = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Error'][1]/following-sibling::*[1]")));
                    String txtfail = failElements.getText();
                    String actualResult = "FAIL " + StockName + " - " + txterror + " " + txtfail;
                    excelReader.updateStatus(rowIndex, "FAIL");
                    excelReader.updateResult(rowIndex, actualResult);
                    failCount++;
                    System.out.println("FAIL STOCK " + StockName + " - " + txterror + " " + txtfail);
                    WebElement okElements = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Error'][1]/following-sibling::*[2]/div")));
                    okElements.click();
                    rowIndex++;
                    continue;
                }
                WebElement sendthisorderyes = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@style, 'font-weight: normal;') and text()='OK']")));
                sendthisorderyes.click();

                WebElement ordersendyes = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@style, 'font-weight: normal;') and text()='OK']")));
                ordersendyes.click();
                // Check for error


                Thread.sleep(1000);

                WebElement statusElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-testid='orderstatus-0']")));
                String text = statusElement.getText();
                WebElement stocklot = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-testid='orderlot-0']")));
                String stocklottxt = stocklot.getText();
                WebElement orderprice1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-testid='orderprice-0']")));
                String orderpricetxt = orderprice1.getText();

                if (text.contains("REJECTED")) {
                    WebElement rejects = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-testid='orderreject-0']")));
                    String rejectstatus = rejects.getText();

                    // Handle rejection scenario
                    String actualResult = "REJECTED " + StockName + " - " + rejectstatus;
                    excelReader.updateStatus(rowIndex, "FAIL");
                    excelReader.updateResult(rowIndex, actualResult);
                    failCount++;
                    System.out.println("REJECTED " + StockName + " - " + rejectstatus);
                } else {
                    // If no errors, update Excel with PASS status
                    String actualResult =  StockName + " - " + text;
                    excelReader.updateStatus(rowIndex, "PASS" );
                    excelReader.updateResult(rowIndex, actualResult+" "+stocklottxt+" "+orderpricetxt);
                    passCount++;
                    System.out.println("PASS " + StockName + " - " + text);
                }
            } catch (Exception e) {
                e.printStackTrace();
                // Handle any unexpected exceptions during processing
            }

            rowIndex++;
        }

        // Add summary row to Excel sheet
        excelReader.addSummaryRow(passCount, failCount);
        excelReader.saveChanges();
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

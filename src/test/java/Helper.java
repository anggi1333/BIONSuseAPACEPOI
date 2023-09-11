import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class Helper {
    private WebDriver driver;
    public Helper(WebDriver driver) {
        this.driver = driver;
    }
    public void HeplerWait(int milliseconds) throws InterruptedException {
        Thread.sleep(milliseconds); // Add a delay for the specified milliseconds
    }
    public void HelperUsername (String txtusername) {
        WebElement username =driver.findElement(By.xpath("//*[text()='Username:']/following-sibling::*/div/input"));
        username.clear();
        username.sendKeys(txtusername);
    }
    public void HelperPassword (String txtPassword) {
        WebElement password = driver.findElement(By.xpath("//*[text()='Password:']/following-sibling::*/div/input"));
        password.clear();
        password.sendKeys(txtPassword);
        password.sendKeys(Keys.ENTER);
    }
    public boolean HelperPasswordWrong() {
        try {
            WebElement passwordWrong = driver.findElement(By.xpath("//*[text()='Login Failed!']"));
            return passwordWrong.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false; // Element not found, so the message is not displayed
        }
    }
    public void HelperStartClick() {
        WebElement buttonStart = driver.findElement(By.xpath("//*[text()='Start']"));
        buttonStart.click();
    }

    public void HelperCustomerListClick() {
        WebElement customerlist = driver.findElement(By.xpath("//*[text()='Customer List']"));
        customerlist.click();
    }
    public void HelperCustomerListAddClick() {
        WebElement customerlistadd = driver.findElement(By.xpath("//*[text()='Add']"));
        customerlistadd.click();
    }
    public void HelperCustomerSalesID(String txtCustomerSalesID) throws InterruptedException {
        WebElement CustomerSalesID = driver.findElement(By.xpath("//*[text()='Sales ID *']/parent::*/parent::*/td[2]/div/input"));
        CustomerSalesID.clear();
        CustomerSalesID.sendKeys(txtCustomerSalesID);
        Thread.sleep(2000);
        CustomerSalesID.sendKeys(Keys.ENTER);
    }
    public void HelperDokumentType(String txtDokumentType) throws InterruptedException {
        WebElement DokumentType = driver.findElement(By.xpath("//*[text()='Document Type *']/parent::*/parent::*/td[2]/div/input"));
        DokumentType.clear();
        DokumentType.sendKeys(txtDokumentType);
        Thread.sleep(2000);
        DokumentType.sendKeys(Keys.ENTER);
    }
    public void HelperAccType(String txtAccType) throws InterruptedException {
        WebElement AccType = driver.findElement(By.xpath("//*[text()='Acc. Type *']/parent::*/parent::*/td[2]/div/input"));
        AccType.clear();
        AccType.sendKeys(txtAccType);
        Thread.sleep(2000);
        AccType.sendKeys(Keys.ENTER);
    }
    public void HelperCustID(String txtCustID) throws InterruptedException {
        WebElement CustID = driver.findElement(By.xpath("//*[text()='Cust. ID *']/parent::*/parent::*/td[2]/div/input"));
        CustID.clear();
        CustID.sendKeys(txtCustID);
        Thread.sleep(500);
        CustID.sendKeys(Keys.ENTER);
    }
    public void HelperInvestorType(String txtInvastorType) throws InterruptedException {
        WebElement InvestorType = driver.findElement(By.xpath("//*[text()='Investor Type *']/parent::*/parent::*/td[2]/div/input"));
        InvestorType.clear();
        InvestorType.sendKeys(txtInvastorType);
        Thread.sleep(2000);
        InvestorType.sendKeys(Keys.ENTER);
    }
    public void HelperMaritalStatus(String txtMaritalStatus) throws InterruptedException {
        WebElement MaritalStatus = driver.findElement(By.xpath("//*[text()='Marital Status *']/parent::*/parent::*/td[2]/div/input"));
        MaritalStatus.clear();
        MaritalStatus.sendKeys(txtMaritalStatus);
        Thread.sleep(2000);
        MaritalStatus.sendKeys(Keys.ENTER);
    }
    public void HelperFirstName(String txtFirstName) throws InterruptedException {
        WebElement FirstName = driver.findElement(By.xpath("//*[text()='First Name: *']/parent::*/div[2]/input"));
        FirstName.clear();
        FirstName.sendKeys(txtFirstName);
        Thread.sleep(500);
        FirstName.sendKeys(Keys.ENTER);
    }
    public void HelperLastName(String txtLastName) throws InterruptedException {
        WebElement LastName = driver.findElement(By.xpath("//*[text()='Last Name:']/parent::*/div[4]/input"));
        LastName.clear();
        LastName.sendKeys(txtLastName);
        Thread.sleep(500);
        LastName.sendKeys(Keys.ENTER);
    }
    public void HelperMrAtauMrs(String txtMrAtauMrs) throws InterruptedException {
        WebElement MrAtauMrs = driver.findElement(By.xpath("//*[text()='Last Name:']/parent::*/div[5]/input"));
        MrAtauMrs.clear();
        MrAtauMrs.sendKeys(txtMrAtauMrs);
        Thread.sleep(2000);
        MrAtauMrs.sendKeys(Keys.ENTER);
    }
    public void HelperReligion(String txtReligion) throws InterruptedException {
        WebElement Religion = driver.findElement(By.xpath("//*[text()='Religion']/parent::*/div[7]/input"));
        Religion.clear();
        Religion.sendKeys(txtReligion);
        Thread.sleep(2000);
        Religion.sendKeys(Keys.ENTER);
    }
    public void HelperBirthPlace(String txtBirthPlace) throws InterruptedException {
        WebElement BirthPlace = driver.findElement(By.xpath("//*[text()='Birth Plc & Date *']/parent::*/parent::*/td[4]/div/div[2]/div[1]/div[1]/input"));
        BirthPlace.clear();
        BirthPlace.sendKeys(txtBirthPlace);
        Thread.sleep(500);
        BirthPlace.sendKeys(Keys.ENTER);
    }
    public void HelperBirthDate(String txtBirthDate) throws InterruptedException {
        WebElement BirthDate = driver.findElement(By.xpath("//*[text()='Birth Plc & Date *']/parent::*/parent::*/td[4]/div/div[2]/div[1]/div[2]/input"));
        BirthDate.clear();
        BirthDate.sendKeys(txtBirthDate);
        Thread.sleep(2000);
        BirthDate.sendKeys(Keys.ENTER);
    }
    public void HelperIDType(String txtIDType) throws InterruptedException {
        WebElement IDType = driver.findElement(By.xpath("//*[text()='ID Type']/parent::*/parent::*/td[2]/div/input"));
        IDType.clear();
        IDType.sendKeys(txtIDType);
        Thread.sleep(2000);
        IDType.sendKeys(Keys.ENTER);
    }
    public void HelperIDNo(String txtIDNo) throws InterruptedException {
        WebElement IDNo = driver.findElement(By.xpath("//*[text()='ID No & Expired *']/parent::*/parent::*/td[4]/div/div[2]/div[1]/div[1]/input"));
        IDNo.clear();
        IDNo.sendKeys(txtIDNo);
        Thread.sleep(500);
        IDNo.sendKeys(Keys.ENTER);
    }
    public void HelperIDDate(String txtIDDate) throws InterruptedException {
        WebElement IDDate = driver.findElement(By.xpath("//*[text()='ID No & Expired *']/parent::*/parent::*/td[4]/div/div[2]/div[1]/div[2]/input"));
        IDDate.clear();
        IDDate.sendKeys(txtIDDate);
        Thread.sleep(500);
        IDDate.sendKeys(Keys.ENTER);
    }
    public void HelperNationality(String txtNationality) throws InterruptedException {
        WebElement Nationality = driver.findElement(By.xpath("//*[text()='Nationality']/parent::*/parent::*/td[2]/div/input"));
        Nationality.clear();
        Nationality.sendKeys(txtNationality);
        Thread.sleep(2000);
        Nationality.sendKeys(Keys.ENTER);
    }
    public void HelperTaxNo(String txtTaxNo) throws InterruptedException {
        WebElement TaxNo = driver.findElement(By.xpath("//*[text()='Tax No.']/parent::*/parent::*/td[4]/div/input"));
        TaxNo.clear();
        TaxNo.sendKeys(txtTaxNo);
        Thread.sleep(500);
        TaxNo.sendKeys(Keys.ENTER);
    }
    public void HelperEducational(String txtEducational) throws InterruptedException {
        WebElement Educational = driver.findElement(By.xpath("//*[text()='Educational Background *']/parent::*/parent::*/td[2]/div/input"));
        Educational.clear();
        Educational.sendKeys(txtEducational);
        Thread.sleep(2000);
        Educational.sendKeys(Keys.ENTER);
    }
    public void HelperMother(String txtMother) throws InterruptedException {
        WebElement Mother = driver.findElement(By.xpath("//*[contains(text(), \"Mother's Name\")]/parent::*/parent::*/td[6]/div/input"));
        Mother.clear();
        Mother.sendKeys(txtMother);
        Thread.sleep(500);
        Mother.sendKeys(Keys.ENTER);
    }
    public void HelperPlace(String txtPlace) throws InterruptedException {
        WebElement Place = driver.findElement(By.xpath("//*[text()='Place/Date of Issuance *']/parent::*/parent::*/td[6]/div/div[2]/div[1]/div[1]/input"));
        Place.clear();
        Place.sendKeys(txtPlace);
        Thread.sleep(500);
        Place.sendKeys(Keys.ENTER);
    }
    public void HelperDate(String txtDate) throws InterruptedException {
        WebElement Date = driver.findElement(By.xpath("//*[text()='Place/Date of Issuance *']/parent::*/parent::*/td[6]/div/div[2]/div[1]/div[2]/input"));
        Date.clear();
        Date.sendKeys(txtDate);
        Thread.sleep(500);
        Date.sendKeys(Keys.ENTER);
    }
    public void HelperCustomerType(String txtCustomerType) throws InterruptedException {
        WebElement CustomerType = driver.findElement(By.xpath("//*[text()='Customer Type *']/parent::*/parent::*/td[4]/div/input"));
        CustomerType.clear();
        CustomerType.sendKeys(txtCustomerType);
        Thread.sleep(500);
        CustomerType.sendKeys(Keys.ENTER);
    }







}

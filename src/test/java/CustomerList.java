import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.*;
//import org.openqa.selenium.support.ui.Select;


public class CustomerList {
    public static void csl(WebDriver driver, ExtentTest extentTest, String csid,String doktype,String acctype,String custid)throws InterruptedException {

        Helper helper = new Helper(driver);
        helper.HeplerWait(2000);
        helper.HelperStartClick();
        helper.HelperCustomerListClick();
        helper.HelperCustomerListAddClick();
        helper.HeplerWait(2000);
        helper.HelperCustomerSalesID(csid);
        helper.HelperDokumentType(doktype);
        helper.HelperAccType(acctype);
        helper.HelperCustID(custid);
        helper.HelperCustomerType("Retail");
        helper.HelperInvestorType("Indonesian");
        helper.HelperFirstName("Anggi");
        helper.HelperLastName("Firmansah");
        helper.HelperMrAtauMrs("Mr");
        helper.HelperReligion("ISLAM / MOSLEM");
        helper.HelperMaritalStatus("Single");
        helper.HelperBirthPlace("Jakarta");
        helper.HelperBirthDate("13/03/1993");
        helper.HelperIDType("ID CARD");
        helper.HelperIDNo("3100001313130001");
        helper.HelperIDDate("31/12/9998");
        helper.HelperNationality("INDONESIAN");
        helper.HelperTaxNo("00000000");
        helper.HelperEducational("S1 / Degree");
        helper.HelperMother("Ibuku");
        helper.HelperPlace("Jakarta");
        helper.HelperDate("05/09/2023");
    }
}

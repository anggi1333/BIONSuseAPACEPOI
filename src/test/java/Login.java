import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class Login {
    public static void login(WebDriver driver,ExtentTest extentTest, String un, String pw ) throws InterruptedException {

        Helper helper = new Helper(driver);

        helper.HelperUsername(un);
        helper.HelperPassword(pw);
        helper.HeplerWait(2000);

        boolean HelperPasswordWrong = helper.HelperPasswordWrong();

        if (HelperPasswordWrong) {
            extentTest.log(Status.FAIL,"Password Salah");
            System.out.println("Password Salah");
            return;
        } else {
            extentTest.log(Status.PASS,"Berhasil Login");
            System.out.println("Berhasil Login");
        }



    }
}

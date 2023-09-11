import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Run {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.setBinary("D:\\chrome-win\\chrome.exe");

        WebDriver driver = new ChromeDriver(options);
        driver.get("http://192.168.1.32:8080/oaccess/");
        driver.manage().window().maximize();

        ExtentReports extent = new ExtentReports();
        ExtentTest loginex = extent.createTest("Login", "Login");
        ExtentTest cutomerlistex = extent.createTest("Customer List", "Customer List");

        Login.login(driver, loginex,"faisal","Password1212");
        CustomerList.csl(driver,cutomerlistex,"160800","Individual","Reguler","test");
    }
}

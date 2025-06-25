import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class hrmAdminPage {
    static Logger logger = LogManager.getLogger("test_orangehrm");
    public static void main(String[] args) {
        logger.info("Bắt đầu test case");
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
            logger.info("Da truy cap vao website");
            WebElement username = driver.findElement(By.name("username"));
            username.sendKeys("Admin");
            Thread.sleep(2000);
            WebElement password = driver.findElement(By.name("password"));
            password.sendKeys("admin123");
            Thread.sleep(2000);
            driver.findElement(By.xpath("//button[@type='submit']")).click();
            logger.info("Dang nhap thanh cong");
            Thread.sleep(2000);
            driver.findElement(By.xpath("//ul[contains(@class, 'oxd-main-menu')]//span[text()='PIM']")).click();
            logger.info("Click PIM thanh cong");
            Thread.sleep(2000);
            driver.findElement(By.xpath("//span[text()='Leave']")).click();
            logger.info("Click Leave thanh cong");
            Thread.sleep(2000);
            driver.findElement(By.xpath("//span[text()='Admin']")).click();
            logger.info("Click Admin thanh cong");
            Thread.sleep(2000);
            driver.findElement(By.xpath("//label[contains(text(),'Username')]/following::input[contains(@class,'oxd-input')]")).sendKeys("username");
            logger.info("Nhap lieu username thanh cong");
            Thread.sleep(2000);
            driver.findElement(By.xpath("//label[contains(text(),'User Role')]/following::*[contains(@class,'oxd-select-text-input')][1]")).click();
            driver.findElement(By.xpath("//div[@role='listbox']//span[text()='Admin']")).click();
            logger.info("Click Admin danh muc user role thanh cong");
            Thread.sleep(2000);
            driver.findElement(By.xpath("//label[contains(text(),'User Role')]/following::*[contains(@class,'oxd-select-text-input')][1]")).click();
            driver.findElement(By.xpath("//div[@role='listbox']//span[text()='ESS']")).click();
            logger.info("Click ESS danh muc user role thanh cong");
            Thread.sleep(2000);
            driver.findElement(By.xpath("//label[contains(text(),'Employee Name')]/following::*[@placeholder='Type for hints...']")).sendKeys("employeename");
            logger.info("Nhap lieu employee name thanh cong");
            Thread.sleep(2000);
            driver.findElement(By.xpath("//button[text()=' Reset ']")).click();
            logger.info("Click reset thanh cong");
            Thread.sleep(2000);
            driver.findElement(By.xpath("//button[text()=' Search ']")).click();
            logger.info("Click search thanh cong");
            Thread.sleep(2000);
            driver.findElement(By.xpath("//span[contains(text(),'User Management ')]")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//ul[@role='menu']//a[@role='menuitem']")).click();
            logger.info("Click users danh muc user management thanh cong");
            Thread.sleep(2000);
            WebElement recordsFound = driver.findElements(By.tagName("span")).get(20);
            if (!recordsFound.getText().equals(" (1) Records Found")) {
                WebElement user = driver.findElement(By.xpath("(//div[@role='row'])[3]"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", user);
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", user);
                String User = user.getText();
                String arraysUser[] = User.split("\n");
                logger.info("Username: " + arraysUser[0] + " - User Role: " + arraysUser[1] + " - Employee Name: " + arraysUser[2]);
                Thread.sleep(2000);
                driver.findElement(By.xpath("(//*[@class='oxd-table-cell-actions'])[2]//i[contains(@class, 'bi-trash')]")).click();
                Thread.sleep(2000);
                driver.findElement(By.xpath("//button[text()=' Yes, Delete ']")).click();
                logger.info("Click xoa thanh cong");
                Thread.sleep(10000);
                driver.findElement(By.xpath("(//*[@class='oxd-table-cell-actions'])[2]//i[contains(@class, 'bi-pencil-fill')]")).click();
                Thread.sleep(2000);
            } else {
                WebElement user = driver.findElement(By.xpath("(//div[@role='row'])[2]"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", user);
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", user);
                String User = user.getText();
                String arraysUser[] = User.split("\n");
                logger.info("Username: " + arraysUser[0] + " - User Role: " + arraysUser[1] + " - Employee Name: " + arraysUser[2]);
                Thread.sleep(2000);
                driver.findElement(By.xpath("(//*[@class='oxd-table-cell-actions'])[1]//i[contains(@class, 'bi-trash')]")).click();
                logger.info("Click xoa thanh cong");
                Thread.sleep(2000);
                driver.findElement(By.xpath("(//*[@class='oxd-table-cell-actions'])[1]//i[contains(@class, 'bi-pencil-fill')]")).click();
                Thread.sleep(2000);
            }
            WebElement editUser = driver.findElements(By.tagName("h6")).get(1);
            if (editUser.getText().contains("Edit User")) {
                logger.info("Click Edit thanh cong");
            } else {
                logger.info("Click Edit that bai");
            }
            Thread.sleep(2000);
            driver.findElement(By.xpath("//span[text()='Admin']")).click();
            Thread.sleep(2000);
            WebElement userRole = driver.findElement(By.xpath("(//div[@role='row'])[1]//div[text()='User Role']"));
            logger.info("Hien thi " + userRole.getText());
            Thread.sleep(2000);
            WebElement employeeName = driver.findElement(By.xpath("(//div[@role='row'])[1]//div[text()='Employee Name']"));
            logger.info("Hien thi " + employeeName.getText());
            Thread.sleep(2000);
            WebElement admin = driver.findElement(By.tagName("h6"));
            WebElement userManagement = driver.findElements(By.tagName("h6")).get(1);
            logger.info("Hien thi " + admin.getText() + " / " + userManagement.getText());
            Thread.sleep(2000);
            driver.findElement(By.xpath("//i[contains(@class, 'bi-chevron-left')]")).click();
            logger.info("Dong danh muc thanh cong");
            Thread.sleep(2000);
            driver.findElement(By.xpath("//button[@type='button']//i[contains(@class,'bi-caret-up-fill')]")).click();
            logger.info("Thu gon thanh cong");
            Thread.sleep(2000);
            driver.findElement(By.xpath("//button[text()=' Add ']")).click();
            Thread.sleep(2000);
            WebElement addUser = driver.findElements(By.tagName("h6")).get(1);
            if (addUser.getText().contains("Add User")) {
                logger.info("Click Add thanh cong");
            } else {
                logger.info("Click Add that bai");
            }
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}

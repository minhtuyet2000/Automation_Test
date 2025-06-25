import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Scanner;

public class amazonSearchResultPage {
    static Logger logger = LogManager.getLogger("test_amazon");
    public static WebDriver driver;
    public static void main(String[] args) {
        logger.info("Bắt đầu test case");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        try {
            driver.get("https://www.amazon.com/");
            logger.info("Da truy cap vao website");
            Scanner scan = new Scanner(System.in);
            List<WebElement> captchaText = driver.findElements(By.xpath("//h4[contains(text(), 'Enter the characters')]"));
            if (!captchaText.isEmpty()) {
                System.out.print("Vui long nhap captcha: ");
                String captCha = scan.next();
                driver.findElement(By.xpath("//input[@placeholder='Type characters']")).sendKeys(captCha.toUpperCase());
                Thread.sleep(2000);
                driver.findElement(By.xpath("//button[@type='submit']")).click();
                logger.info("Nhap lieu captcha thanh cong");
            }
            Thread.sleep(3000);
            driver.findElement(By.xpath("//span[@aria-label='United States']")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//span[text()='AR']")).click();
            logger.info("Click tieng a rap thanh cong");
            Thread.sleep(2000);
            driver.findElement(By.xpath("//span[text()='EN']")).click();
            logger.info("Click tieng anh thanh cong");
            Thread.sleep(2000);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='twotabsearchtextbox']")));
            search.sendKeys("adidas");
            search.submit();
            logger.info("Search thanh cong");
            Thread.sleep(2000);
            for (int i=1;i<=4;i++) {
                thongtin(i);
                Thread.sleep(1000);
            }
            driver.findElement(By.xpath("(//div[contains(@style,'!important;')])[3]//a[@tabindex='-1']")).click();
            logger.info("Click hinh anh danh muc Results thanh cong");
            Thread.sleep(2000);
            driver.navigate().back();
            Thread.sleep(2000);
            WebElement gioHang = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='nav-cart-count-container']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", gioHang);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", gioHang);
            driver.findElement(By.xpath("//title"));
            if (driver.getTitle().contains("Shopping Cart")) {
                logger.info("Click gio hang thanh cong");
            } else {
                logger.info("Click gio hang that bai");
            }
            Thread.sleep(2000);
            driver.navigate().back();
            Thread.sleep(5000);
            WebElement categories = driver.findElement(By.xpath("(//ol[contains(@class,'carousel')])[1]//li[@aria-label='2 of 5']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", categories);
            categories.click();
            driver.findElement(By.xpath("//title"));
            if (driver.getTitle().contains("womens")) {
                logger.info("Click womens footwear danh muc Shop adidas categories thanh cong");
            } else {
                logger.info("Click womens footwear danh muc Shop adidas categories that bai");
            }
            Thread.sleep(2000);
            driver.navigate().back();
//            Thread.sleep(2000);
//            WebElement juniors = driver.findElement(By.xpath("//li[@id='p_n_shoe_width_browse-vebin/13130370011']//input[@type='checkbox']"));
//            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", juniors);
//            String isjuniorsChecked = juniors.getAttribute("checked");
//            if (isjuniorsChecked == null) {
//                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", juniors);
//                logger.info("Click juniors danh muc Special Clothing Size thanh cong");
//            } else {
//                logger.info("juniors danh muc Special Clothing Size da duoc chon");
//            }
//            Thread.sleep(2000);
//            driver.navigate().back();
            Thread.sleep(2000);
            WebElement topAdidas = driver.findElement(By.xpath("//h2[@id='loom-desktop-top-slot_us-slds-sp-2-t1-a2-heading']"));
            logger.info("Hien thi " + topAdidas.getText());
            Thread.sleep(2000);
            WebElement men = driver.findElement(By.xpath("//li[@id='p_n_feature_thirty-two_browse-bin/121075132011']//input[contains(@type,'checkbox')]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", men);
            String ismenChecked = men.getAttribute("checked");
            if (ismenChecked == null) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", men);
                logger.info("Click men danh muc Gender thanh cong");
            } else {
                logger.info("men danh muc Gender da duoc chon");
            }
            Thread.sleep(2000);
            driver.navigate().back();
            Thread.sleep(2000);
            WebElement results = driver.findElement(By.xpath("(//h2[contains(@class,'a-text-normal')])[1]"));
            logger.info("Hien thi " + results.getText());
            Thread.sleep(2000);
            WebElement bags = driver.findElement(By.xpath("(//ol[contains(@class,'carousel')])[2]//li[@aria-label='5 of 6']"));
            logger.info("Hien thi " + bags.getText() + " danh muc " + topAdidas.getText());
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
    public static void thongtin(int vitri) {
        logger.info("----------------- San pham thu: " + vitri + " --------------------");
        List<WebElement> nhanHang = driver.findElements(By.xpath("(//div[contains(@class,'s-title-instructions-style')])["+vitri+"]//div[contains(@class,'a-color-secondary')]"));
        List<WebElement> tenSanPham = driver.findElements(By.xpath("(//div[contains(@class,'s-title-instructions-style')])["+vitri+"]//a[contains(@class,'a-text-normal')]"));
        List<WebElement> giaDaoDong = driver.findElements(By.xpath("(//div[contains(@class,'s-price-instructions-style')])["+vitri+"]//span[contains(@class,'a-price-range')]"));
        List<WebElement> giaSanPham = driver.findElements(By.xpath("(//div[contains(@class,'s-price-instructions-style')])["+vitri+"]//span[@data-a-size='xl']"));
        List<WebElement> giaTuongDuong = driver.findElements(By.xpath("(//div[contains(@class,'s-price-instructions-style')])["+vitri+"]//span[text()='/count)']"));
        List<WebElement> giaGoc = driver.findElements(By.xpath("(//div[contains(@class,'s-price-instructions-style')])["+vitri+"]//div[contains(@aria-hidden,'List: ') or contains(@aria-hidden,'Typical: ')]"));
        List<WebElement> vanChuyen = driver.findElements(By.xpath("(//div[contains(@class,'puis-padding-right-small')])["+vitri+"]//span[contains(@aria-label,'Delivery')]"));
        if (!nhanHang.isEmpty()) {
            for (WebElement nhan_Hang : nhanHang) {
                logger.info("Nhan hang: " + nhan_Hang.getText().replace("\n", " "));
            }
        }
        if (!tenSanPham.isEmpty()) {
            for (WebElement ten_San_Pham : tenSanPham) {
                logger.info("Ten san pham: " + ten_San_Pham.getText().replace("\n", " "));
            }
        }
        if (!giaDaoDong.isEmpty()) {
            for (WebElement gia_Dao_Dong : giaDaoDong) {
                logger.info("Gia dao dong: " + gia_Dao_Dong.getText().replace("\n", " "));
            }
        } else if (!giaSanPham.isEmpty()) {
            for (WebElement gia_San_Pham : giaSanPham) {
                logger.info("Gia san pham: " + gia_San_Pham.getText().replace("\n", " "));
            }
        }
        if (!giaTuongDuong.isEmpty()) {
            for (WebElement gia_Tuong_Duong : giaTuongDuong) {
                logger.info("Gia tuong duong: " + gia_Tuong_Duong.getText().replace("\n", " "));
            }
        }
        if (!giaGoc.isEmpty()) {
            for (WebElement gia_Goc : giaGoc) {
                logger.info("Gia goc: " + gia_Goc.getText().replace("\n", " "));
            }
        }
        if (!vanChuyen.isEmpty()) {
            for (WebElement van_Chuyen : vanChuyen) {
                logger.info("Van chuyen: " + van_Chuyen.getText().replace("\n", " "));
            }
        }
        logger.info("------------------------------------------------------");
    }
}

package org.example;

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

public class airbnbHomePage {
    static Logger logger = LogManager.getLogger("test_airbnb");
    public static void main(String[] args) {
        logger.info("Bắt đầu test case");
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        try {
            driver.get("https://demo4.cybersoft.edu.vn/");
            logger.info("Da truy cap vao website");
            Thread.sleep(2000);
            driver.findElement(By.xpath("//span[text()='airbnb']")).click();
            logger.info("Click airbnb thanh cong");
            Thread.sleep(2000);
            driver.findElement(By.xpath("//a[text()='Home']")).click();
            logger.info("Click home thanh cong");
            Thread.sleep(2000);
            driver.findElement(By.xpath("//a[text()='About']")).click();
            logger.info("Click about thanh cong");
            Thread.sleep(2000);
            driver.findElement(By.xpath("//img[contains(@class,'h-10')]")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//img[contains(@class,'h-10')]")).click();
            logger.info("Click avatar thanh cong");
            Thread.sleep(2000);
            driver.findElement(By.xpath("//div[contains(@class,'col-span-3')][1]")).click();
            Thread.sleep(2000);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement diaDiem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='Hồ Chí Minh']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", diaDiem);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", diaDiem);
            logger.info("Click dia diem thành công");
            Thread.sleep(2000);
            driver.findElement(By.xpath("//div[contains(@class,'grid-cols-12')]/div[3]")).click();
            Thread.sleep(5000);
            driver.findElement(By.xpath("//div[contains(@class,'rdrDays')]//button[not(contains(@class,'rdrDayPassive'))]//span[text()='14']")).click();
            driver.findElement(By.xpath("//button[not(contains(@class,'rdrDayPassive'))]//span[text()='16']")).click();
            Thread.sleep(5000);
            logger.info("Click thoi gian thành công");
            driver.findElement(By.xpath("//div[contains(@class,'col-span-3 flex-1 p-3 flex')]")).click();
            logger.info("Click them khach thành công");
            Thread.sleep(2000);
            driver.findElement(By.xpath("//button/div[text()='+']")).click();
            logger.info("Them so luong khach thanh cong");
            Thread.sleep(2000);
            driver.findElement(By.xpath("//button/div[text()='-']")).click();
            logger.info("Giam so luong khach thanh cong");
            Thread.sleep(2000);
            driver.findElement(By.xpath("//span[@aria-label='search']")).click();
            Thread.sleep(2000);
            WebElement search = driver.findElements(By.tagName("p")).get(0);
            if (search.getText().contains("Danh sách phòng")) {
                logger.info("Chua nhap dia diem");
            } else if (search.getText().contains("Không tìm thấy phòng")) {
                logger.info("Khong tim thay phong phu hop voi so luong khach");
            } else if (search.getText().contains("Hồ Chí Minh")) {
                logger.info("Tim duoc phong");
            } else {
                logger.info("Tim kiem that bai");
            }
            driver.findElement(By.xpath("//span[text()='airbnb']")).click();
            Thread.sleep(2000);
            WebElement hoChiMinh = driver.findElement(By.xpath("(//div[contains(@class,'css-mzwlov')]//div[contains(@class,'ant-card-body')])[1]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", hoChiMinh);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", hoChiMinh);
            logger.info("Click hochiminh thanh cong");
            Thread.sleep(2000);
            driver.findElement(By.xpath("//span[text()='airbnb']")).click();
            Thread.sleep(2000);
            WebElement canTho = driver.findElement(By.xpath("(//div[contains(@class,'css-mzwlov')]//div[contains(@class,'ant-card-body')])[2]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", canTho);
            String CanTho = canTho.getText();
            String arraysCanTho[] = CanTho.split("\n");
            logger.info("Dia diem: " + arraysCanTho[0] + " - Thoi gian: " + arraysCanTho[1]);
            Thread.sleep(2000);
            driver.findElement(By.xpath("//button[text()='Loại nơi ở']")).click();
            logger.info("Click loai noi o thanh cong");
            Thread.sleep(2000);
            driver.findElement(By.xpath("//button[text()='Giá']")).click();
            logger.info("Click gia thanh cong");
            Thread.sleep(2000);
            WebElement nhaTrang = driver.findElement(By.xpath("(//div[contains(@class,'css-mzwlov')]//div[contains(@class,'ant-card-body')])[3]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", nhaTrang);
            String NhaTrang = nhaTrang.getText();
            String arraysNhaTrang[] = NhaTrang.split("\n");
            logger.info("Dia diem: " + arraysNhaTrang[0] + " - Thoi gian: " + arraysNhaTrang[1]);
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}

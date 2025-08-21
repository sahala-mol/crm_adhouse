package testingcrm;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class adhouse {

    public static void main(String[] args) {
        // Setup ChromeDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();

        // Set Chrome for Testing binary path
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Users\\hp\\chromedriver\\chrome-win64\\chrome-win64\\chrome.exe");;

        // Initialize WebDriver
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        // Navigate to the login page
        driver.get("https://www.ad.house-academy.scaleup-business-builder.xyz/");

        // Setup explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Enter credentials
        driver.findElement(By.name("username")).sendKeys("super-admin");
        driver.findElement(By.name("password")).sendKeys("123456");

        // Click "Remember my preference" checkbox via JavaScript
        WebElement checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//label[contains(text(),'Remember my preference')]/preceding-sibling::input")
        ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);

        // ✅ Wait for the splash screen to disappear
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("splash-screen")));

        // ✅ Now wait for the login button and click it
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//button[contains(text(),'Sign Me In')]")
        ));
        loginButton.click();
package net.team.artezio.selenium;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumApplicationTests {
    WebDriver driver;


    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        driver = new ChromeDriver();
    }

    @Test
    public void helloWorldTest() {
        driver.get("https://artteam-pe-team-qa.oo-aps.artezio.net");
    }

    @After
    public void close(){
        driver.close();
    }
}


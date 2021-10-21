package net.team.artezio.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class SeleniumApplicationTests {

    WebDriver driver;
    static final String APP_URL = "https://artteam-pe-team-qa.oo-aps.artezio.net";
    static final String HOST_URL = "http://localhost:4444/wd/hub";

    @Before
    public void setUp(){
        ChromeOptions opt = new ChromeOptions();
        try {
            driver = new RemoteWebDriver(new URL(HOST_URL), opt);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(APP_URL);
    }

    @Test
    public void assertTitle(){
        String expectedTitle = "Log in to Keycloak";
        assertEquals(driver.getTitle(), expectedTitle);
    }

    @After
    public void tearDown(){
        if(driver != null) {
            driver.quit();
        }
    }
}

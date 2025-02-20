package webHooks;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import config.ConfigReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class CucumberHooks {

    @Before("@jira")
    public void initBrowser() {
        Configuration.timeout = 15000;
        Configuration.browser = ConfigReader.getProperty("browser");

        Selenide.open();
        getWebDriver().manage().window().maximize();

        Selenide.open(ConfigReader.getProperty("url"));
    }

    @After("@jira")
    public void afterTest() {
        Selenide.closeWebDriver();
    }
}

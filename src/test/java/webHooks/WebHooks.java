package webHooks;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import config.ConfigReader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class WebHooks {

    @BeforeEach
    public void initBrowser() {
        Configuration.timeout = 15000;

        Selenide.open();
        getWebDriver().manage().window().maximize();

        Selenide.open(ConfigReader.getProperty("url"));
    }

    @AfterEach
    public void afterTest() {
        Selenide.closeWebDriver();
    }
}

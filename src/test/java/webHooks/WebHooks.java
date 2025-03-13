package webHooks;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.ConfigReader;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class WebHooks {

    @BeforeAll
    public static void initAllureListener() {
        SelenideLogger.addListener(
                "AllureListener",
                new AllureSelenide()
                        .screenshots(Boolean.parseBoolean(ConfigReader.getProperty("logger.save.screenshot")))
                        .savePageSource(Boolean.parseBoolean(ConfigReader.getProperty("logger.save.page.source"))));
    }

    @BeforeEach
    @Step("Открытие браузера")
    public void initBrowser() {
        Configuration.timeout = 15000;

        Selenide.open();
        getWebDriver().manage().window().maximize();

        Selenide.open(ConfigReader.getProperty("url"));
    }

    @AfterEach
    @Step("Закрытие браузера")
    public void afterTest() {
        Selenide.closeWebDriver();
    }
}

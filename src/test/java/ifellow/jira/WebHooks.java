package ifellow.jira;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.PageLoadStrategy;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class WebHooks {
    @BeforeEach
    public void initBrowser() {
        Configuration.pageLoadStrategy = PageLoadStrategy.EAGER.toString();
        Configuration.browser = "chrome";
        Configuration.timeout = 15000;

        Selenide.open();
        getWebDriver().manage().window().maximize();

        Selenide.open("https://edujira.ifellow.ru");
    }

    @AfterEach
    public void afterTest() {
        Selenide.closeWebDriver();
    }
}

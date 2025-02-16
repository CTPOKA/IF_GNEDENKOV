package webHooks;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class CucumberHooks {
    private static final Properties props = new Properties();

    static {
        try (InputStream input = Files.newInputStream(Paths.get("src/main/java/config/Config.properties"))) {
            props.load(input);
        } catch (IOException e) {
            System.err.println("Ошибка загрузки конфигурации: " + e.getMessage());
        }
    }

    @Before("@jira")
    public void initBrowser() {
        Configuration.timeout = 15000;
        Configuration.browser = props.getProperty("browser");

        Selenide.open();
        getWebDriver().manage().window().maximize();

        Selenide.open(props.getProperty("url"));
    }

    @After("@jira")
    public void afterTest() {
        Selenide.closeWebDriver();
    }
}

package webHooks;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class WebHooks {
    private static final Properties props = new Properties();

    static {
        try (InputStream input = Files.newInputStream(Paths.get("src/main/java/config/ConfigProperties"))) {
            props.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка загрузки конфигурации", e);
        }
    }

    @BeforeEach
    public void initBrowser() {
        Configuration.timeout = 15000;

        Selenide.open();
        getWebDriver().manage().window().maximize();

        Selenide.open(props.getProperty("url"));
    }

    @AfterEach
    public void afterTest() {
        Selenide.closeWebDriver();
    }
}

package steps;

import io.cucumber.java.ru.*;
import org.junit.jupiter.api.Assertions;
import pages.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class LoginSteps {
    private static final Properties props = new Properties();

    static {
        try (InputStream input = Files.newInputStream(Paths.get("src/main/java/config/Config.properties"))) {
            props.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка загрузки конфигурации", e);
        }
    }

    private final LoginPage jiraLoginPage = new LoginPage();
    private final MainPage jiraMainPage = new MainPage();

    private final String login = props.getProperty("login");
    private final String password = props.getProperty("password");

    @Допустим("пользователь заходит в Jira")
    public void loginToJira() {
        jiraLoginPage.login(login, password);
    }

    @Тогда("он должен увидеть свой профиль")
    public void verifySuccessfulLogin() {
        Assertions.assertEquals(login, jiraMainPage.getCurrentUser());
    }
}

package steps;

import config.ConfigReader;
import io.cucumber.java.ru.*;
import org.junit.jupiter.api.Assertions;
import pages.*;

public class LoginSteps {
    private final LoginPage jiraLoginPage = new LoginPage();
    private final MainPage jiraMainPage = new MainPage();

    @Допустим("пользователь заходит в Jira")
    public void loginToJira() {
        jiraLoginPage.login(ConfigReader.getProperty("login"), ConfigReader.getProperty("password"));
    }

    @Тогда("он должен увидеть свой профиль")
    public void verifySuccessfulLogin() {
        Assertions.assertEquals(ConfigReader.getProperty("login"), jiraMainPage.getCurrentUser());
    }
}

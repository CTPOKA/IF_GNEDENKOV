package steps;

import config.ConfigReader;
import io.cucumber.java.ru.*;
import org.junit.jupiter.api.Assertions;
import pages.*;

public class LoginSteps {
    private final LoginPage jiraLoginPage = new LoginPage();
    private final MainPage jiraMainPage = new MainPage();

    private final String login = ConfigReader.getProperty("login");
    private final String password = ConfigReader.getProperty("password");

    @Допустим("пользователь заходит в Jira")
    public void loginToJira() {
        jiraLoginPage.login(login, password);
    }

    @Тогда("он должен увидеть свой профиль")
    public void verifySuccessfulLogin() {
        Assertions.assertEquals(login, jiraMainPage.getCurrentUser());
    }
}

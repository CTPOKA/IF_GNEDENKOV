package pagesTest;

import com.codeborne.selenide.Selenide;
import config.ConfigReader;
import org.junit.jupiter.api.*;
import pages.*;
import webHooks.WebHooks;

public class JiraTest extends WebHooks {

    private final LoginPage jiraLoginPage = new LoginPage();
    private final MainPage jiraMainPage = new MainPage();
    private final ProjectPage jiraProjectPage = new ProjectPage();
    private final IssuePage jiraIssuePage = new IssuePage();
    private final CreateIssuePage jiraCreateIssuePage = new CreateIssuePage();

    private final String login = ConfigReader.getProperty("login");
    private final String password =  ConfigReader.getProperty("password");

    private final String projectName = ConfigReader.getProperty("projectName");
    private final String issueName = ConfigReader.getProperty("issueName");

    @Test
    @DisplayName("Проверка успешной аутентификации пользователя")
    public void successfulLoginTest() {
        jiraLoginPage.login(login, password);

        Assertions.assertEquals(login, jiraMainPage.getCurrentUser());
    }

    @Test
    @DisplayName("Проверка открытия проекта")
    public void openProjectTest() {
        jiraLoginPage.login(login, password)
                .openProject(projectName);

        Assertions.assertTrue(jiraProjectPage.getCurrentProjectName().equalsIgnoreCase(projectName));
    }

    @Test
    @DisplayName("Проверка счетчика задач в проекте")
    public void issuesCounterTest() {
        String issueName = "tmp";

        jiraLoginPage.login(login, password).openProject(projectName);

        int issuesCount1 = jiraProjectPage.getIssuesCount();

        jiraMainPage.openCreateIssuePage()
                .setSummary(issueName)
                .pressCreateIssueButton();

        String issueFullName = jiraCreateIssuePage.getLastCreatedIssueFullName();

        Selenide.refresh();
        int issuesCount2 = jiraProjectPage.getIssuesCount();

        jiraMainPage.openIssue(issueName, issueFullName).deleteIssue();
        jiraMainPage.openProject(projectName);

        int issuesCount3 = jiraProjectPage.getIssuesCount();

        Assertions.assertEquals(issuesCount1 + 1, issuesCount2, "Значение счетчика не увеличилось на 1");
        Assertions.assertEquals(issuesCount2 - 1, issuesCount3, "Значение счетчика не уменьшилось на 1");
    }

    @Test
    @DisplayName("Проверка состояния задачи")
    public void checkIssueTest() {
        jiraLoginPage.login(login, password)
                .openIssue(issueName);

        Assertions.assertTrue(jiraIssuePage.getIssueStatus().equalsIgnoreCase("Сделать"), "Неверный статус задачи");
        Assertions.assertTrue(jiraIssuePage.getIssueFixVersion().equalsIgnoreCase("Version 2.0"), "Неверная версия");
    }

    @Test
    @DisplayName("Проверка создания баг-репорта")
    public void createBugreportTest() {
        String issueName = "тест создания бага";
        String description = "описание";
        String environment = "окружение";
        String fixVersion = "Version 2.0";

        jiraLoginPage.login(login, password)

                .openCreateIssuePage()
                .setType("Ошибка")
                .setSummary(issueName)
                .addFixVersion(fixVersion)
                .setDescription(description)
                .setEnvironment(environment)
                .pressCreateIssueButton()

                .openIssue(issueName, jiraCreateIssuePage.getLastCreatedIssueFullName())
                .setIssueToDone();

        Assertions.assertTrue(jiraIssuePage.getIssueStatus().equalsIgnoreCase("Готово"), "Неверный статус задачи");
        Assertions.assertEquals(description, jiraIssuePage.getIssueDescription(),"Неверное описание задачи");
        Assertions.assertEquals(environment, jiraIssuePage.getIssueEnvironment(),"Неверное окружение задачи");
        Assertions.assertEquals(fixVersion, jiraIssuePage.getIssueFixVersion(),"Неверная версия");
    }

}

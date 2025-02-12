package ifellow.jira;

import ifellow.jira.pages.*;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FirstTest extends WebHooks {

    private final LoginPage jiraLoginPage = new LoginPage();
    private final MainPage jiraMainPage = new MainPage();
    private final ProjectPage jiraProjectPage = new ProjectPage();
    private final IssuePage jiraIssuePage = new IssuePage();
    private final CreateIssuePage jiraCreateIssuePage = new CreateIssuePage();

    private final String login = "AT8";
    private final String password = "Qwerty123";

    private final String projectName = "Test";

    private final String issueName = "TestSeleniumATHomework";

    @Test
    @Order(1)
    @DisplayName("Проверка успешной авторизации пользователя")
    public void successfulLoginTest() {
        jiraLoginPage
                .login(login, password);

        Assertions.assertEquals(login, jiraMainPage.getCurrentUser(),
                "Имена пользователей не совпадают");
    }

    @Test
    @Order(2)
    @DisplayName("Проверка открытия проекта")
    public void openProjectTest() {
        jiraLoginPage
                .login(login, password)
                .openProject(projectName);

        Assertions.assertTrue(jiraProjectPage.getCurrentProjectName().equalsIgnoreCase(projectName),
                "Названия проектов не совпадают");
    }

    @Test
    @Order(3)
    @DisplayName("Проверка счетчика задач в проекте")
    public void issuesCounterTest() {
        jiraLoginPage
                .login(login, password)
                .openProject(projectName)
                .changeFilter("Мои открытые задачи")
                .waitForIssue();

        int issuesCount = jiraProjectPage.getIssuesCount();

        jiraIssuePage.setIssueToDone();
        jiraProjectPage
                .changeFilter("Мои открытые задачи")
                .waitForIssue();

        Assertions.assertEquals(issuesCount - 1, jiraProjectPage.getIssuesCount(),
                "Значение счетчика не уменьшилось на 1");

        issuesCount = jiraProjectPage.getIssuesCount();

        jiraProjectPage.changeFilter("Недавно решенные");
        jiraIssuePage.setIssueToInProgress();
        jiraProjectPage
                .changeFilter("Мои открытые задачи")
                .waitForIssue();

        Assertions.assertEquals(issuesCount + 1, jiraProjectPage.getIssuesCount(),
                "Значение счетчика не увеличилось на 1");
    }

    @Test
    @Order(4)
    @DisplayName("Проверка состояния задачи")
    public void checkIssueTest() {
        jiraLoginPage
                .login(login, password)
                .openIssue(issueName);

        Assertions.assertTrue(jiraIssuePage.getCurrentIssueStatus().equalsIgnoreCase("Сделать"),
                "Неверный статус задачи");
        Assertions.assertTrue(jiraIssuePage.getCurrentIssueFixVersion().equalsIgnoreCase("Version 2.0"),
                "Неверная версия");
    }

    @Test
    @Order(5)
    @DisplayName("Проверка создания баг-репорта")
    public void createBugreportTest() {
        String issueName = "авария";

        jiraLoginPage
                .login(login, password)

                .openCreateIssuePage()
                .setSummary(issueName)
                .setType("Ошибка")
                .setDescription("текст")
                .setPriority("Low")
                .setEnvironment("текст")
                .setSeverity(0)
                .addFixVersion("Version 1.0")
                .addFixVersion("Version 2.0")
                .createIssue()

                .openIssue(issueName, jiraCreateIssuePage.getLastCreatedIssueFullName())
                .setIssueToDone();

        Assertions.assertTrue(jiraIssuePage.getCurrentIssueStatus().equalsIgnoreCase("Готово"),
                "Статус задачи не изменен на 'Готово'");
    }

}

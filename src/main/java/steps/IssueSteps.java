package steps;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.ru.*;
import org.junit.jupiter.api.Assertions;
import pages.*;

public class IssueSteps {
    private final MainPage jiraMainPage = new MainPage();
    private final ProjectPage jiraProjectPage = new ProjectPage();
    private final IssuePage jiraIssuePage = new IssuePage();

    private int issuesCountBefore;

    @Когда("он создает новую задачу {string}")
    public void createNewIssue(String issueName) {
        issuesCountBefore = jiraProjectPage.getIssuesCount();

        jiraMainPage.openCreateIssuePage()
                .setSummary(issueName)
                .pressCreateIssueButton();

        Selenide.refresh();
    }

    @Тогда("счетчик задач должен увеличиться на 1")
    public void verifyIssueCounterIncrement() {
        int issuesCountAfter = jiraProjectPage.getIssuesCount();
        Assertions.assertEquals(issuesCountBefore + 1, issuesCountAfter, "Счетчик задач не увеличился");
    }

    @Когда("он удаляет задачу {string}")
    public void deleteIssue(String issueName) {
        String projectName = jiraProjectPage.getCurrentProjectName();
        jiraMainPage.openIssue(issueName, issueName).deleteIssue();
        jiraMainPage.openProject(projectName);
    }

    @Тогда("счетчик задач должен уменьшиться на 1")
    public void verifyIssueCounterDecrement() {
        int issuesCountAfterDelete = jiraProjectPage.getIssuesCount();
        Assertions.assertEquals(issuesCountBefore, issuesCountAfterDelete, "Счетчик задач не уменьшился");
    }

    @Когда("он открывает задачу {string}")
    public void openIssue(String issueName) {
        jiraMainPage.openIssue(issueName);
    }

    @Тогда("статус задачи должен быть {string}")
    public void verifyIssueStatus(String expectedStatus) {
        Assertions.assertTrue(jiraIssuePage.getIssueStatus().equalsIgnoreCase(expectedStatus), "Неверный статус задачи");
    }

    @И("версия задачи должна быть {string}")
    public void verifyIssueVersion(String expectedVersion) {
        Assertions.assertTrue(jiraIssuePage.getIssueFixVersion().equalsIgnoreCase(expectedVersion), "Неверная версия задачи");
    }
}

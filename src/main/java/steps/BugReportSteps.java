package steps;

import io.cucumber.java.ru.*;
import org.junit.jupiter.api.Assertions;
import pages.*;

public class BugReportSteps {
    private final MainPage jiraMainPage = new MainPage();
    private final CreateIssuePage jiraCreateIssuePage = new CreateIssuePage();
    private final IssuePage jiraIssuePage = new IssuePage();

    @Когда("он создает баг-репорт с параметрами:")
    public void createBugReport(io.cucumber.datatable.DataTable table) {
        var data = table.asMaps().get(0);

        jiraMainPage.openCreateIssuePage()
                .setType("Ошибка")
                .setSummary(data.get("Заголовок"))
                .addFixVersion(data.get("Версия"))
                .setDescription(data.get("Описание"))
                .setEnvironment(data.get("Окружение"))
                .pressCreateIssueButton()
                .openIssue(data.get("Заголовок"), jiraCreateIssuePage.getLastCreatedIssueFullName());
    }

    @Когда("он устанавливает статус бага в \"Готово\"")
    public void setBugReportToDone() {
        jiraIssuePage.setIssueToDone();
    }

    @Тогда("описание должно быть {string}")
    public void verifyIssueDescription(String expectedDescription) {
        Assertions.assertEquals(expectedDescription, jiraIssuePage.getIssueDescription(), "Неверное описание задачи");
    }

    @И("окружение должно быть {string}")
    public void verifyIssueEnvironment(String expectedEnvironment) {
        Assertions.assertEquals(expectedEnvironment, jiraIssuePage.getIssueEnvironment(), "Неверное окружение задачи");
    }

    @И("версия должна быть {string}")
    public void verifyIssueFixVersion(String expectedFixVersion) {
        Assertions.assertEquals(expectedFixVersion, jiraIssuePage.getIssueFixVersion(), "Неверная версия задачи");
    }
}

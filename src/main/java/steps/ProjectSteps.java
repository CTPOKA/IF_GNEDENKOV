package steps;

import io.cucumber.java.ru.*;
import org.junit.jupiter.api.Assertions;
import pages.*;

public class ProjectSteps {
    private final MainPage jiraMainPage = new MainPage();
    private final ProjectPage jiraProjectPage = new ProjectPage();

    @Когда("он открывает проект {string}")
    public void openProject(String projectName) {
        jiraMainPage.openProject(projectName);
    }

    @Тогда("текущий проект должен быть {string}")
    public void verifyCurrentProject(String expectedProject) {
        Assertions.assertTrue(jiraProjectPage.getCurrentProjectName().equalsIgnoreCase(expectedProject));
    }
}

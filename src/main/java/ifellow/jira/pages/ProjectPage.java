package ifellow.jira.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class ProjectPage {

    private final SelenideElement projectNameLabel = $x("//a[@id='project-name-val']")
            .as("Название текущего проекта");
    private final SelenideElement issuesCounterLabel = $x("//div[@class='pager-container']//span")
            .as("Счетчик задач в проекте");
    private final SelenideElement issuesFilterDropdownButton = $x("//button[@id='subnav-trigger']")
            .as("Кнопка открытия списка фильтров задач");
    private final SelenideElement issuesFilterOptions = $x("//div[@id='subnav-opts']")
            .as("Выпадающий список фильтров задач");
    private final SelenideElement issuesFilterLabel = $x("//span[@id='issues-subnavigation-title']")
            .as("Текущий выбранный фильтр задач");

    public ProjectPage waitForIssue() {
        projectNameLabel.shouldBe(Condition.visible, Duration.ofSeconds(15));
        Selenide.sleep(1000);
        return this;
    }

    public String getCurrentProjectName() {
        return projectNameLabel
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .text();
    }

    public int getIssuesCount() {
        return Integer.parseInt(issuesCounterLabel.text().split(" ")[2]);
    }

    public ProjectPage changeFilter(String filter) {
        issuesFilterDropdownButton
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .click();
        issuesFilterOptions
                .shouldBe(Condition.visible, Duration.ofSeconds(3))
                .$x(String.format(".//a[text()='%s']", filter))
                .click();
        issuesFilterLabel.shouldBe(Condition.text(filter), Duration.ofSeconds(15));
        return this;
    }
}

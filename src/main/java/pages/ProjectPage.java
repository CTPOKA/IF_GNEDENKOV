package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class ProjectPage {

    private final SelenideElement projectNameLabel = $x("//a[@id='project-name-val']")
            .as("Название текущего проекта");
    private final SelenideElement issuesCounterLabel = $x("//div[@class='pager-container']//span")
            .as("Счетчик задач в проекте");

    public String getCurrentProjectName() {
        return projectNameLabel.shouldBe(Condition.visible, Duration.ofSeconds(15)).text();
    }

    public int getIssuesCount() {
        return Integer.parseInt(issuesCounterLabel.text().split(" ")[2]);
    }
}

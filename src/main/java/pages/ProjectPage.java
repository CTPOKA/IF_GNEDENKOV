package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class ProjectPage {

    private final SelenideElement projectNameLabel = $x("//a[@id='project-name-val']")
            .as("Название текущего проекта");
    private final SelenideElement issuesCounterLabel = $x("//div[@class='pager-container']//span")
            .as("Счетчик задач в проекте");

    @Step("Получить название текущего проекта")
    public String getCurrentProjectName() {
        return projectNameLabel.shouldBe(Condition.visible, Duration.ofSeconds(15)).text();
    }

    @Step("Получить количество задач в проекте")
    public int getIssuesCount() {
        return Integer.parseInt(issuesCounterLabel.shouldBe(Condition.visible, Duration.ofSeconds(15)).text().split(" ")[2]);
    }
}

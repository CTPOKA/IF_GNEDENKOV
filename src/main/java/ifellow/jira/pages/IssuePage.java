package ifellow.jira.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class IssuePage {
    private final SelenideElement issueStatusLabel = $x("//span[@id='status-val']/span")
            .as("Статус текущей задачи");
    private final SelenideElement fixVersionLabel = $x("//span[@id='fixVersions-field']/a")
            .as("Версия исправления задачи");
    private final SelenideElement issueStatusDropdownButton = $x("//a[@id='opsbar-transitions_more']")
            .as("Кнопка смены статуса задачи");
    private final SelenideElement issueResolvedButton = $x("//aui-dropdown-menu[@id='opsbar-transitions_more_drop']//span[text()='Выполнено']")
            .as("Кнопка перевода задачи в статус 'Выполнено'");
    private final SelenideElement issueInProgressButton = $x("//a[contains(@class, 'issueaction-workflow-transition')]//span[text()='В работе']")
            .as("Кнопка перевода задачи в статус 'В работе'");

    public IssuePage setIssueToDone() {
        issueStatusDropdownButton
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .click();
        issueResolvedButton
                .shouldBe(Condition.visible, Duration.ofSeconds(3))
                .click();
        issueStatusLabel.shouldBe(Condition.text("Готово"), Duration.ofSeconds(15));
        return this;
    }

    public IssuePage setIssueToInProgress() {
        issueInProgressButton
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .click();
        issueStatusLabel.shouldBe(Condition.text("В работе"), Duration.ofSeconds(15));
        return this;
    }

    public String getCurrentIssueStatus() {
        return issueStatusLabel
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .text();
    }

    public String getCurrentIssueFixVersion() {
        return fixVersionLabel
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .text();
    }
}

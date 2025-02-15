package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class IssuePage {
    private final SelenideElement issueStatusLabel = $x("//span[@id='status-val']/span")
            .as("Статус текущей задачи");
    private final SelenideElement fixVersionLabel = $x("//span[@id='fixVersions-field']/a")
            .as("Версия исправления задачи");
    private final SelenideElement descriptionLabel = $x("//div[@id='description-val']//p")
            .as("Описание задачи");
    private final SelenideElement environmentLabel = $x("//div[@id='environment-val']//p")
            .as("Окружение задачи");
    private final SelenideElement issueStatusDropdownButton = $x("//a[@id='opsbar-transitions_more']")
            .as("Кнопка смены статуса задачи");
    private final SelenideElement issueResolvedButton = $x("//aui-dropdown-menu[@id='opsbar-transitions_more_drop']//span[text()='Выполнено']")
            .as("Кнопка перевода задачи в статус 'Выполнено'");
    private final SelenideElement moreButton = $x("//a[@id='opsbar-operations_more']")
            .as("Кнопка дополнительных опций");
    private final SelenideElement deleteButton = $x("//aui-item-link[@id='delete-issue']")
            .as("Кнопка удаления задачи");
    private final SelenideElement deleteSubmitButton = $x("//input[@id='delete-issue-submit']");

    public void setIssueToDone() {
        pressStatusDropdownButton();
        issueResolvedButton.shouldBe(Condition.visible).click();
        issueStatusLabel.shouldBe(Condition.text("Готово"));
    }

    public void pressStatusDropdownButton() {
        issueStatusDropdownButton.shouldBe(Condition.visible, Duration.ofSeconds(15)).click();
    }

    public void deleteIssue() {
        pressMoreButton();
        deleteButton.shouldBe(Condition.visible).click();
        deleteSubmitButton.shouldBe(Condition.visible).click();
    }

    public void pressMoreButton() {
        moreButton.shouldBe(Condition.visible, Duration.ofSeconds(15)).click();
    }

    public String getIssueStatus() {
        return issueStatusLabel.shouldBe(Condition.exist, Duration.ofSeconds(15)).text();
    }

    public String getIssueDescription() {
        return descriptionLabel.shouldBe(Condition.exist, Duration.ofSeconds(15)).text();
    }

    public String getIssueEnvironment() {
        return environmentLabel.shouldBe(Condition.exist, Duration.ofSeconds(15)).text();
    }

    public String getIssueFixVersion() {
        return fixVersionLabel.shouldBe(Condition.exist, Duration.ofSeconds(15)).text();
    }
}

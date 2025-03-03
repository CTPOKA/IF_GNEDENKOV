package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

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
    private final SelenideElement deleteSubmitButton = $x("//input[@id='delete-issue-submit']")
            .as("Подтверждение удаления задачи");

    @Step("Перевести задачу в статус 'Готово'")
    public void setIssueToDone() {
        pressStatusDropdownButton();
        issueResolvedButton.shouldBe(Condition.visible).click();
        issueStatusLabel.shouldBe(Condition.text("Готово"));
    }

    @Step("Нажать на кнопку смены статуса задачи")
    public void pressStatusDropdownButton() {
        issueStatusDropdownButton.shouldBe(Condition.visible, Duration.ofSeconds(15)).click();
    }

    @Step("Удалить задачу")
    public void deleteIssue() {
        pressMoreButton();
        deleteButton.shouldBe(Condition.visible).click();
        deleteSubmitButton.shouldBe(Condition.visible).click();
    }

    @Step("Открыть меню дополнительных опций задачи")
    public void pressMoreButton() {
        moreButton.shouldBe(Condition.visible, Duration.ofSeconds(15)).click();
    }

    @Step("Получить статус задачи")
    public String getIssueStatus() {
        return issueStatusLabel.shouldBe(Condition.exist, Duration.ofSeconds(15)).text();
    }

    @Step("Получить описание задачи")
    public String getIssueDescription() {
        return descriptionLabel.shouldBe(Condition.exist, Duration.ofSeconds(15)).text();
    }

    @Step("Получить окружение задачи")
    public String getIssueEnvironment() {
        return environmentLabel.shouldBe(Condition.exist, Duration.ofSeconds(15)).text();
    }

    @Step("Получить версию исправления задачи")
    public String getIssueFixVersion() {
        return fixVersionLabel.shouldBe(Condition.exist, Duration.ofSeconds(15)).text();
    }
}

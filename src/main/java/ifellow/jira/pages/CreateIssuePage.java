package ifellow.jira.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class CreateIssuePage {
    private final SelenideElement summaryInput = $x("//input[@id='summary']")
            .as("Поле ввода заголовка задачи");
    private final SelenideElement descriptionTextarea = $x("//textarea[@id='description']")
            .as("Поле ввода описания задачи");
    private final SelenideElement environmentTextarea = $x("//textarea[@id='environment']")
            .as("Поле ввода окружения задачи");
    private final SelenideElement fixVersionsSelect = $x("//select[@id='fixVersions']")
            .as("Выпадающий список версий");
    private final SelenideElement priorityInput = $x("//input[@id='priority-field']")
            .as("Поле приоритета задачи");
    private final SelenideElement typeInput = $x("//input[@id='issuetype-field']")
            .as("Поле типа задачи");
    private final SelenideElement severitySelect = $x("//label[text()='Серьезность']/following-sibling::select")
            .as("Выпадающий список серьезности");
    private final SelenideElement createIssueButton = $x("//input[@id='create-issue-submit']")
            .as("Кнопка создания задачи");
    private final SelenideElement issueCreationMessage = $x("//a[contains(@class, 'issue-created-key')]")
            .as("Уведомление о создании задачи");
    private final SelenideElement closeCreationMessageButton = issueCreationMessage.$x("./following-sibling::button[@class='aui-close-button']")
            .as("Кнопка закрытия уведомления");

    public MainPage createIssue() {
        createIssueButton.shouldBe(Condition.visible, Duration.ofSeconds(15))
                .scrollTo()
                .click();
        closeCreationMessageButton.shouldBe(Condition.visible, Duration.ofSeconds(15))
                .click();
        return Selenide.page(MainPage.class);
    }

    public CreateIssuePage setSummary(String summary) {
        summaryInput.shouldBe(Condition.visible, Duration.ofSeconds(15))
                .scrollTo()
                .sendKeys(summary);
        return this;
    }

    public CreateIssuePage setDescription(String description) {
        fillTextareaVisual(descriptionTextarea, description);
        return this;
    }

    public CreateIssuePage setEnvironment(String environment) {
        fillTextareaVisual(environmentTextarea, environment);
        return this;
    }

    public CreateIssuePage addFixVersion(String version) {
        fixVersionsSelect
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .scrollTo()
                .$x(String.format(".//option[normalize-space(text())='%s']", version))
                .as("Выбор версии: " + version)
                .click();
        return this;
    }

    public CreateIssuePage setSeverity(int severityLevel) {
        severitySelect.shouldBe(Condition.visible, Duration.ofSeconds(15))
                .$x(String.format(".//option[starts-with(text(), '%s')]", "S" + severityLevel))
                .as("Выбор серьезности S" + severityLevel)
                .scrollTo()
                .click();
        return this;
    }

    public CreateIssuePage setPriority(String priority) {
        priorityInput.shouldBe(Condition.visible, Duration.ofSeconds(15))
                .scrollTo()
                .click();
        priorityInput.sendKeys(priority);
        return this;
    }

    public CreateIssuePage setType(String type) {
        typeInput.shouldBe(Condition.visible, Duration.ofSeconds(15))
                .scrollTo()
                .click();
        typeInput.sendKeys(type);
        return this;
    }

    public String getLastCreatedIssueFullName() {
        return issueCreationMessage
                .shouldBe(Condition.exist, Duration.ofSeconds(15))
                .text();
    }

    private void fillTextareaVisual(SelenideElement textarea, String text) {
        textarea.shouldBe(Condition.exist, Duration.ofSeconds(15))
                .$x("./following-sibling::nav//button[text()='Визуальный']")
                .scrollTo()
                .click();
        textarea
                .scrollTo()
                .sendKeys(text);
    }
}

package ifellow.jira.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class MainPage {
    private final SelenideElement projectsDropdownButton = $x("//a[@id='browse_link']")
            .as("Кнопка открытия списка проектов");
    private final SelenideElement projectsList = $x("//div[@id='browse_link-content']")
            .as("Список проектов");
    private final SelenideElement searchInput = $x("//input[@id='quickSearchInput']")
            .as("Поле быстрого поиска");
    private final SelenideElement searchResultsList = $x("//div[@class='quicksearch-dropdown']")
            .as("Список результатов поиска");
    private final SelenideElement userNameMeta = $x("//meta[@name='ajs-remote-user']")
            .as("Имя пользователя");
    private final SelenideElement createIssueButton = $x("//a[@id='create_link']")
            .as("Кнопка создания задачи");

    public CreateIssuePage openCreateIssuePage() {
        createIssueButton.shouldBe(Condition.visible, Duration.ofSeconds(15))
                .click();
        return Selenide.page(CreateIssuePage.class);
    }

    public ProjectPage openProject(String projectName) {
        projectsDropdownButton.shouldBe(Condition.visible, Duration.ofSeconds(15))
                .click();
        projectsList
                .shouldBe(Condition.visible, Duration.ofSeconds(3))
                .$x(String.format(".//a[starts-with(text(), '%s')]", projectName + " ("))
                .click();
        return Selenide.page(ProjectPage.class);
    }

    public IssuePage openIssue(String IssueName) {
        projectsDropdownButton.shouldBe(Condition.visible, Duration.ofSeconds(15));
        return openIssue(IssueName, IssueName);
    }

    public IssuePage openIssue(String issueName, String searchText) {
        search(issueName, searchText).click();
        return Selenide.page(IssuePage.class);
    }

    public String getCurrentUser() {
        return userNameMeta.shouldBe(Condition.attributeMatching("content", ".+"), Duration.ofSeconds(15))
                .getAttribute("content");
    }

    private SelenideElement search(String title, String searchText) {
        searchInput
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .sendKeys(searchText);
        return searchResultsList
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .$x(String.format(".//li[@original-title='%s']", title));
    }
}

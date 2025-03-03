package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {

    private final SelenideElement loginInput = $x("//input[@id='login-form-username']")
            .as("Поле ввода логина");
    private final SelenideElement passwordInput = $x("//input[@id='login-form-password']")
            .as("Поле ввода пароля");
    private final SelenideElement loginButton = $x("//input[contains(@id,'login') and @type='submit']")
            .as("Кнопка входа");

    @Step("Авторизация пользователя: {login}")
    public MainPage login(String login, String password) {
        setFieldCredential(login, password).pressLoginButton();
        return Selenide.page(MainPage.class);
    }

    @Step("Ввод учетных данных: логин = {login}, пароль = ****")
    public LoginPage setFieldCredential(String login, String password) {
        loginInput.shouldBe(Condition.visible, Duration.ofSeconds(15)).sendKeys(login);
        passwordInput.shouldBe(Condition.visible).sendKeys(password);
        return this;
    }

    @Step("Нажать кнопку входа")
    public void pressLoginButton() {
        loginButton.shouldBe(Condition.visible).click();
    }
}

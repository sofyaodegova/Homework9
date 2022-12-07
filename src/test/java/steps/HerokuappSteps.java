package steps;

import PageObjects.*;
import PageObjects.MainPage;
import com.codeborne.selenide.Condition;
import cucumber.api.java.ru.Дано;
import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.Тогда;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class HerokuappSteps {


    @Дано("^Открываю главную страницу сайта$")
    public void openUrl() {
        MainPage.openMainPage();
    }

    @Дано("^Перехожу на страницу Checkboxes$")
    public void checkboxesLink() {
        Checkboxes.checkboxesLink.click();
        System.out.println("Переходим на страницу /checkboxes");
    }

    @Когда("^Нажимаю на чекбокс №1$")
    public void clickCheckbox() {
        Checkboxes.checkbox1.click();
        System.out.println("Выбираем чекбокс 1");
    }

    @Тогда("^Проверяю, что чекбокс №1 нажат$")
    public void checkCheckbox() {
        Checkboxes.checkbox1.shouldBe(Condition.selected);
        System.out.println("Проверка нажатия: чекбокс 1 нажат");
    }

    @Дано("^Перехожу на страницу FormAuthentication$")
    public void formAuthenticationLink() {
        FormAuthentication.formAuthenticationUrl.click();
        System.out.println("Перехожу на страницу с формой аутентификации");
    }

    @Дано("^Корректный логин '(.+)' и пароль '(.+)'$")
    public void validLoginPassword(String username, String password) {
        FormAuthentication.usernameForm.val(username);
        System.out.println("Заполняю корректный логин " + username);
        FormAuthentication.passwordForm.val(password);
        System.out.println("Заполняю корректный пароль " + password);
    }

    @Когда("^Нажимаю на кнопку логин$")
    public void loginClick() {
        FormAuthentication.loginButton.click();
        System.out.println("Нажимаю на кнопку логин");
    }

    @Тогда("^Вижу надпись You logged into a secure area!$")
    public void loginLogout() {
        FormAuthentication.successText.shouldBe(Condition.visible);
        System.out.println("You logged into a secure area!");
        FormAuthentication.logoutButton.click();
        System.out.println("Выхожу из аккаунта");
    }

    @Дано("^Некорректный логин '(.+)' и пароль '(.+)'$")
    public void invalidLoginPassword(String wrongUsername, String wrongPassword) {
        FormAuthentication.usernameForm.val(wrongUsername);
        System.out.println("Заполняю корректный логин " + wrongUsername);
        FormAuthentication.passwordForm.val(wrongPassword);
        System.out.println("Заполняю корректный пароль " + wrongPassword);
    }

    @Тогда("^Вижу надпись Your password is invalid!$")
    public void вижу_надпись_Your_password_is_invalid() throws Throwable {
        FormAuthentication.errorText.shouldBe(Condition.visible);
        System.out.println("Your password is invalid!");
    }


    @Дано("^Перехожу на страницу Hovers$")
    public void hoversLink() {
        Hovers.hovers.click();
        System.out.println("Переходим на страницу /hovers");
    }

    @Когда("^Навожу курсор на изображение №2$")
    public void hoverImg() {
        Hovers.img.hover();
        System.out.println("Навожу курсор на изображение №2");
    }

    @Тогда("^Вижу имя пользователя №2 и ссылку на просмотр профиля$")
    public void checkHover() {
        Hovers.name.shouldBe(Condition.visible);
        Hovers.viewProfile.shouldBe(Condition.visible);
        System.out.println("Вижу имя пользователя №2 и ссылку на профиль №2");
    }

    @Дано("^Перехожу на страницу DynamicallyLoadedPage$")
    public void DynamicallyLoadedPageLink() {
        DynamicallyLoadedPage.dynamicLoading.click();
        System.out.println("Переходим на страницу /dynamic_loading");
    }

    @Дано("^Перехожу на второй пример$")
    public void clickExample2() {
        DynamicallyLoadedPage.example2.click();
        System.out.println("Переходим на страницу /dynamic_loading/2");
    }

    @Когда("^Нажимаю на кнопку старт$")
    public void clickStart() {
        DynamicallyLoadedPage.startButton.click();
        System.out.println("Нажимаю на кнопку старт");
    }

    @Тогда("^Загрузка страницы должна пропасть$")
    public void loadingDisappear() {
        DynamicallyLoadedPage.loading.shouldBe(disappear, Duration.ofMillis(6000));
    }

    @Тогда("^Вижу надпись Hello World!$")
    public void helloWorldText() {
        DynamicallyLoadedPage.helloWorldText.shouldBe(visible);
        System.out.println("Элемент hello world существует");
    }

    @Дано("^Перехожу на страницу KeyPresses$")
    public void keyPressesLink() {
        KeyPresses.keyPresses.click();
        System.out.println("Переходим на страницу /key_presses");
    }

    @Когда("^Ввожу букву '(.+)'$")
    public void enterText(String text) {
        KeyPresses.textForm.sendKeys(text);
    }

    @Тогда("^Вижу надпись '(.+)'$")
    public void visibleText(String text) {
        $(byText("You entered: " + text.toUpperCase())).shouldBe(Condition.visible);
        System.out.println("Видим текст: " + text.toUpperCase());
    }

    @Когда("^Ввожу символ '(.+)'$")
    public void enterSymbol(String symbol) {
        Keys enteredSymbol = Keys.valueOf(symbol);
        KeyPresses.textForm.sendKeys(enteredSymbol);
    }

    @Тогда("^Вижу символ '(.+)'$")
    public void visibleSymbol(String symbol) {
        $(byText("You entered: " + symbol)).shouldBe(Condition.visible);
        System.out.println("Видим символ: " + symbol);
    }

    @Дано("^Перехожу на страницу Add/Remove Elements$")
    public void addRemoveLink() {
        AddRemoveElements.addRemoveElements.click();
        System.out.println("Переходим на страницу /add_remove_elements");
    }

    @Когда("^Нажимаю на кнопку Add '(.+)' раз$")
    public void clickAddButton(int count) {
        for (int i = 0; AddRemoveElements.deleteButton.size() != count; i++) {
            AddRemoveElements.addElementButton.click();
            AddRemoveElements.deleteButton.get(i).shouldBe(Condition.visible);
        }
    }

    @Тогда("Добавляется кнопка Delete '(.+)' раз$")
    public void checkDeleteButton(int count) {
        System.out.println("Добавили кнопку delete " + count + " раз");
    }
}

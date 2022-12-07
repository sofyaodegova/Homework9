package steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
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

    public static int count;

    @Дано("^Открываю главную страницу сайта$")
    public void openUrl() {
        MainPage.openMainPage();
    }

    @Дано("^Перехожу на страницу Checkboxes$")
    public void checkboxesLink() {
        SelenideElement checkboxesLink = $x("//*[text()=\"Checkboxes\"]");
        checkboxesLink.click();
        System.out.println("Переходим на страницу /checkboxes");
    }

    @Когда("^Нажимаю на чекбокс №1$")
    public void clickCheckbox() {
        SelenideElement checkbox1 = $x("//*[text()=\" checkbox 1\"]/input");
        checkbox1.click();
        System.out.println("Выбираем чекбокс 1");
    }

    @Тогда("^Проверяю, что чекбокс №1 нажат$")
    public void checkCheckbox() {
        SelenideElement checkbox1 = $x("//*[text()=\" checkbox 1\"]/input");
        checkbox1.shouldBe(Condition.selected);
        System.out.println("Проверка нажатия: чекбокс 1 нажат");
    }

    @Дано("^Перехожу на страницу FormAuthentication$")
    public void formAuthenticationLink() {
        SelenideElement formAuthenticationUrl = $x("//*[text()=\"Form Authentication\"]");
        formAuthenticationUrl.click();
        System.out.println("Перехожу на страницу с формой аутентификации");
    }

    @Дано("^Корректный логин '(.+)' и пароль '(.+)'$")
    public void validLoginPassword(String username, String password) {
        SelenideElement usernameForm = $x("//*[@id=\"username\"]");
        SelenideElement passwordForm = $x("//*[@id=\"password\"]");

        usernameForm.val(username);
        System.out.println("Заполняю корректный логин " + username);
        passwordForm.val(password);
        System.out.println("Заполняю корректный пароль " + password);
    }

    @Когда("^Нажимаю на кнопку логин$")
    public void loginClick() {
        SelenideElement loginButton = $x("//*[@id=\"login\"]/button");
        loginButton.click();
        System.out.println("Нажимаю на кнопку логин");
    }

    @Тогда("^Вижу надпись You logged into a secure area!$")
    public void loginLogout() {
        SelenideElement successText = $x("//*[@class=\"flash success\"]");
        SelenideElement logoutButton = $x("//*[text()=\" Logout\"]");
        successText.shouldBe(Condition.visible);
        System.out.println("You logged into a secure area!");
        logoutButton.click();
        System.out.println("Выхожу из аккаунта");
    }

    @Дано("^Некорректный логин '(.+)' и пароль '(.+)'$")
    public void invalidLoginPassword(String wrongUsername, String wrongPassword) {
        SelenideElement usernameForm = $x("//*[@id=\"username\"]");
        SelenideElement passwordForm = $x("//*[@id=\"password\"]");

        usernameForm.val(wrongUsername);
        System.out.println("Заполняю корректный логин " + wrongUsername);
        passwordForm.val(wrongPassword);
        System.out.println("Заполняю корректный пароль " + wrongPassword);
    }

    @Тогда("^Вижу надпись Your password is invalid!$")
    public void вижу_надпись_Your_password_is_invalid() throws Throwable {
        SelenideElement errorText = $x("//*[@class=\"flash error\"]");
        errorText.shouldBe(Condition.visible);
        System.out.println("Your password is invalid!");
    }


    @Дано("^Перехожу на страницу Hovers$")
    public void hoversLink() {
        SelenideElement hovers = $x("//*[text()=\"Hovers\"]");
        hovers.click();
        System.out.println("Переходим на страницу /hovers");
    }

    @Когда("^Навожу курсор на изображение №2$")
    public void hoverImg() {
        SelenideElement img = $x("*//div[2]/img");
        img.hover();
        System.out.println("Навожу курсор на изображение №2");
    }

    @Тогда("^Вижу имя пользователя №2 и ссылку на просмотр профиля$")
    public void checkHover() {
        SelenideElement name = $x("//*[text()=\"name: user2\"]");
        SelenideElement viewProfile = $x("//*[@class=\"figure\"]/../..//*[@href=\"/users/2\"]");

        name.shouldBe(Condition.visible);
        viewProfile.shouldBe(Condition.visible);
        System.out.println("Вижу имя пользователя №2 и ссылку на профиль №2");
    }

    @Дано("^Перехожу на страницу DynamicallyLoadedPage$")
    public void DynamicallyLoadedPageLink() {
        SelenideElement dynamicLoading = $x("//*[text()=\"Dynamic Loading\"]");
        dynamicLoading.click();
        System.out.println("Переходим на страницу /dynamic_loading");
    }

    @Дано("^Перехожу на второй пример$")
    public void clickExample2() {
        SelenideElement example2 = $x("//*[text()=\"Example 2: Element rendered after the fact\"]");
        example2.click();
        System.out.println("Переходим на страницу /dynamic_loading/2");
    }

    @Когда("^Нажимаю на кнопку старт$")
    public void clickStart() {
        SelenideElement startButton = $x("//*[text()=\"Start\"]");
        startButton.click();
        System.out.println("Нажимаю на кнопку старт");
    }

    @Тогда("^Загрузка страницы должна пропасть$")
    public void loadingDisappear() {
        SelenideElement loading = $x("//*[text()=\"Loading... \"]");
        loading.shouldBe(disappear, Duration.ofMillis(6000));
    }

    @Тогда("^Вижу надпись Hello World!$")
    public void helloWorldText() {
        SelenideElement helloWorldText = $x("//*[text()=\"Hello World!\"]");
        helloWorldText.shouldBe(visible);
        System.out.println("Элемент hello world существует");
    }

    @Дано("^Перехожу на страницу KeyPresses$")
    public void keyPressesLink() {
        SelenideElement keyPresses = $x("//*[text()=\"Key Presses\"]");
        keyPresses.click();
        System.out.println("Переходим на страницу /key_presses");
    }

    @Когда("^Ввожу букву '(.+)'$")
    public void enterText(String text) {
        SelenideElement textForm = $x("//*[@id=\"target\"]");
        textForm.sendKeys(text);
    }

    @Тогда("^Вижу надпись '(.+)'$")
    public void visibleText(String text) {
        $(byText("You entered: " + text.toUpperCase())).shouldBe(Condition.visible);
        System.out.println("Видим текст: " + text.toUpperCase());
    }

    @Когда("^Ввожу символ '(.+)'$")
    public void enterSymbol(String symbol) {
        SelenideElement textForm = $x("//*[@id=\"target\"]");
        Keys enteredSymbol = Keys.valueOf(symbol);
        textForm.sendKeys(enteredSymbol);
    }

    @Тогда("^Вижу символ '(.+)'$")
    public void visibleSymbol(String symbol) {
        $(byText("You entered: " + symbol)).shouldBe(Condition.visible);
        System.out.println("Видим символ: " + symbol);
    }

    @Дано("^Перехожу на страницу Add/Remove Elements$")
    public void addRemoveLink() {
        SelenideElement addRemoveElements = $x("//*[text()=\"Add/Remove Elements\"]");
        addRemoveElements.click();
        System.out.println("Переходим на страницу /add_remove_elements");
    }

    @Когда("^Нажимаю на кнопку Add '(.+)' раз$")
    public void clickAddButton(int count) {
        SelenideElement addElementButton = $x("//*[text()=\"Add Element\"]");
        ElementsCollection deleteButton = $$x("//*[text()=\"Delete\"]");

        for (int i = 0; deleteButton.size() != count; i++) {
            addElementButton.click();
            deleteButton.get(i).shouldBe(Condition.visible);
        }
    }

    @Тогда("Добавляется кнопка Delete '(.+)' раз$")
    public void checkDeleteButton(int count) {
        System.out.println("Добавили кнопку delete " + count + " раз");
    }

}

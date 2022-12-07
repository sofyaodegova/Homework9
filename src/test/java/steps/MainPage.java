package steps;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;

public class MainPage {

    public static String baseUrl = "https://the-internet.herokuapp.com/";

    public static void openMainPage() {
        Configuration.holdBrowserOpen = true;
        Selenide.open(baseUrl);
        System.out.println("Открываю страницу: " + baseUrl);
    }
}
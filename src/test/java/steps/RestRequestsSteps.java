package steps;

import cucumber.api.java.ru.Дано;
import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.Тогда;

import io.restassured.response.Response;

import java.util.Map;

public class RestRequestsSteps {

    public static String token;
    public static Response response;
    public static String bookingId;
    public static String updatedBody;
    public Integer expectedStatusCode;

    @Дано("^Логин: '(.+)', пароль: '(.+)'")
    public void getToken(String username, String password) {
        String tokenBody = "{\n" +
                "\"username\":\"" + username + "\",\n" +
                "\"password\":\"" + password + "\"\n" +
                "}";
        response = RestRequests.getToken(tokenBody);
    }

    @Тогда("^Проверяю, что получили токен$")
    public void checkToken() {
        token = response.jsonPath().get("token").toString();
        System.out.println("Получили токен: " + token);
    }

    @Дано("^Создаю бронирование с данными параметрами:$")
    public void createBooking(Map<String, String> arg) {
        String addBody = "{\n" +
                "    \"firstname\": \"Sofya\",\n" +
                "    \"lastname\": \"Odegova\",\n" +
                "    \"totalprice\": 3500,\n" +
                "    \"depositpaid\": true,\n" +
                "    \"bookingdates\": {\n" +
                "        \"checkin\": \"2022-07-13\",\n" +
                "        \"checkout\": \"2022-07-23\"\n" +
                "    },\n" +
                "    \"additionalneeds\": \"Breakfast\"\n" +
                "}";
        response = RestRequests.createBooking(addBody, 200);
    }

    @Тогда("^Проверяю, что было создано бронирование$")
    public void checkBookingId() {
        bookingId = response.jsonPath().getString("bookingid");
        System.out.println("Бронирование создано, id получен: " + bookingId);
    }

    @Когда("^Отправляю запрос на получение созданного бронирования$")
    public void getBookingId() {
        RestRequests.getBooking(bookingId, 200);
        ;
    }

    @Когда("^Обновляю созданное бронирование:$")
    public void updateBooking(Map<String, String> arg) {
        String updatedBody = "{\n" +
                "    \"firstname\": \"Sofya\",\n" +
                "    \"lastname\": \"Odegova\",\n" +
                "    \"totalprice\": 3500,\n" +
                "    \"depositpaid\": true,\n" +
                "    \"bookingdates\": {\n" +
                "        \"checkin\": \"2022-07-13\",\n" +
                "        \"checkout\": \"2022-08-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\": \"Breakfast\"\n" +
                "}";
        response = RestRequests.updateBooking(token, updatedBody, bookingId, 200);
    }

    @Тогда("^Проверяю, что бронирование обновлено$")
    public void checkUpdate() {
        System.out.println("Бронирование c id: " + bookingId + " обновлено");
    }

    @Когда("^Частично обновляю созданное бронирование:$")
    public void partialBookingUpdate(Map<String, String> arg) {
        updatedBody = "{\n" +
                "    \"totalprice\" : 5000\n" +
                "}";
        RestRequests.partialUpdateBooking(token, updatedBody, bookingId, 200);
    }

    @Когда("^Отправляю запрос на удаление существующего бронирования$")
    public void deleteBooking() {
        response = RestRequests.deleteBooking(token, bookingId, 201);
    }

    @Тогда("^Проверяю, бронирование удалено$")
    public void checkBookingDelete() {
        System.out.println("Бронирование удалено. Статус: " + response.statusLine());
        System.out.println("Проверка бронирования. Статус: " + RestRequests.getBooking(bookingId, 404).getStatusLine());
    }

    @Дано("^Некорректный логин: '(.+)', некорректный пароль: '(.+)'")
    public void getTokenNegative(String wrongUsername, String wrongPassword) {
        String tokenBody = "{\n" +
                "\"username\":\"" + wrongUsername + "\",\n" +
                "\"password\":\"" + wrongPassword + "\"\n" +
                "}";
        response = RestRequests.getToken(tokenBody);
    }

    @Тогда("^Проверяю, что не получили токен$")
    public void getReason() {
        String reason = response.jsonPath().get("reason");
        System.out.println("Не получили токен. Причина: " + reason);
    }

    @Когда("^Создаю бронирование с некорректными параметрами:$")
    public void createBookingNegative(Map<String, String> arg) {
        String addBody = "{\n" +
                "    \"firstnammmmme\": \"Sofya\",\n" +
                "    \"lastname\": \"Odegova\",\n" +
                "    \"totalprice\": 3500,\n" +
                "    \"depositpaid\": true,\n" +
                "    \"bookingdates\": {\n" +
                "        \"checkin\": \"2022-07-13\",\n" +
                "        \"checkout\": \"2022-07-23\"\n" +
                "    },\n" +
                "    \"additionalneeds\": \"Breakfast\"\n" +
                "}";
        response = RestRequests.createBooking(addBody, 500);
    }

    @Тогда("^Проверяю, что не было создано бронирование$")
    public void checkBookingNegative() {
        System.out.println("Бронирование не было создано, id не получен. Ошибка: " + response.statusLine());
    }

    @Дано("^Отправляю запрос на получение бронирования с id: '(.+)'")
    public void getBookingNegative(String bookingId) {
        response = RestRequests.getBooking(bookingId, 404);
    }

    @Тогда("^Проверяю, что бронирование не существует$")
    public void checkInvalidBooking() {
        System.out.println("Ошибка: " + response.getStatusLine());
    }

    @Когда("^Удаляю бронирование с id: '(.+)'")
    public void deleteInvalidBooking(String bookingId) {
        response = RestRequests.deleteBooking(token, bookingId, 405);
    }

    @Тогда("^Проверяю, что бронирование не было удалено$")
    public void checkDeleteInvalidBooking() {
        System.out.println("Ошибка удаления. Статус: " + response.statusLine());
    }
}

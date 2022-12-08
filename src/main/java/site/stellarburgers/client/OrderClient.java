package site.stellarburgers.client;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import site.stellarburgers.pojo.CreateOrder;
import site.stellarburgers.pojo.RegisterUser;

import static io.restassured.RestAssured.given;

public class OrderClient extends  Client{

    private final static String ORDERS_PATH = "api/orders";




    @Step("Создание заказа")
    public static ValidatableResponse createOrder(CreateOrder data, String bearerPlusToken) {
        return given()
                .spec(getSpec(bearerPlusToken))
                .body(data)
                .when()
                .post(ORDERS_PATH)
                .then();
    }

    @Step("Получение заказов конкретного пользователя")
    public static ValidatableResponse getUserOrders(String bearerPlusToken) {
        return given()
                .spec(getSpec(bearerPlusToken))
                .when()
                .get(ORDERS_PATH)
                .then();
    }

}

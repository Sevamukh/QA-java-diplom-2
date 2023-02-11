package site.stellarburgers;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import junitparams.JUnitParamsRunner;
import org.junit.*;
import org.junit.runner.RunWith;
import site.stellarburgers.client.OrderClient;
import site.stellarburgers.client.UserClient;
import site.stellarburgers.generator.OrderGenerator;
import site.stellarburgers.generator.UserGenerator;
import site.stellarburgers.pojo.CreateOrder;
import site.stellarburgers.pojo.RegisterUser;

import java.util.List;

import static org.apache.http.HttpStatus.*;

@RunWith(JUnitParamsRunner.class)
public class GetUserOrdersTest {

    private final static RegisterUser registerData = UserGenerator.getDefaultRegistrationData();
    private final static CreateOrder createOrderData = OrderGenerator.getDefaultOrder();
    private static String token = "";
    private int statusCode;
    private boolean isGot;

    @BeforeClass
    public static void setUp() {
        ValidatableResponse responseRegister = UserClient.registerUser(registerData);
        token = responseRegister.extract().path("accessToken");
        ValidatableResponse responseCreateOrder = OrderClient.createOrder(createOrderData, token);
    }

    @AfterClass
    public static void tearDown() {
        ValidatableResponse responseDelete = UserClient.deleteUser(token);
    }

    @Test
    @DisplayName("Получение заказов конкретного авторизованного пользователя")
    public void getAuthorizedUserOrders() {
        ValidatableResponse responseGetOrders = OrderClient.getUserOrders(token);
        statusCode = responseGetOrders.extract().statusCode();
        isGot = responseGetOrders.extract().path("success");
        List<Object> orders = responseGetOrders.extract().path("orders");

        Assert.assertEquals("Ошибка в коде или теле ответа", List.of(SC_OK, true, false),
                List.of(statusCode, isGot, orders.isEmpty()));
    }

    @Test
    @DisplayName("Получение заказов конкретного неавторизованного пользователя")
    public void getUnauthorizedUserOrders() {
        ValidatableResponse responseGetOrders = OrderClient.getUserOrders("abc");
        statusCode = responseGetOrders.extract().statusCode();
        isGot = responseGetOrders.extract().path("success");

        Assert.assertEquals("Ошибка в коде или теле ответа", List.of(SC_UNAUTHORIZED, false),
                List.of(statusCode, isGot));
    }
}

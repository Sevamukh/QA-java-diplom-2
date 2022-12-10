package site.stellarburgers;

import io.restassured.response.ValidatableResponse;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
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
public class CreateOrderTest {

    private final static RegisterUser registerData = UserGenerator.getDefaultRegistrationData();
    private static String token = "";
    private int statusCode;
    private boolean isCreated;
    CreateOrder createOrderData;

    @BeforeClass
    public static void beforeAll(){
        ValidatableResponse responseRegister = UserClient.registerUser(registerData);
        token = responseRegister.extract().path("accessToken");
    }

    @AfterClass
    public static void afterAll(){
        ValidatableResponse responseDelete = UserClient.deleteUser(token);
    }

    @Test
    @Parameters(method = "createOrderParameters")
    @TestCaseName("Создание заказа с авторизацией ({0}) и непустым ({1}) корректным ({2}) списком ингредиентов")
    public void createOrder(boolean isAuth, boolean haveIngredients, boolean isHashCorrect, int status) {
        String token2 = "abc";
        if (isAuth) {token2 = token;}
        if (isHashCorrect) {
            if (haveIngredients) {
                createOrderData = OrderGenerator.getDefaultOrder();
            } else {
                createOrderData = OrderGenerator.getOrderWithoutIngredients();
            }
        } else {
            createOrderData = OrderGenerator.getOrderWithIncorrectHash();
        }
        ValidatableResponse responseCreateOrder = OrderClient.createOrder(createOrderData, token2);

        statusCode = responseCreateOrder.extract().statusCode();
        if (isHashCorrect) {
            isCreated = responseCreateOrder.extract().path("success");
        } else {isCreated = false;}

        Assert.assertEquals("Ошибка в коде или теле ответа",
                List.of(status, haveIngredients & isHashCorrect),
                List.of(statusCode, isCreated));
    }
    private Object[][] createOrderParameters() {
        return new Object[][]{
                {true, true, true, SC_OK},
                {true, true, false, SC_INTERNAL_SERVER_ERROR},
                {true, false, true, SC_BAD_REQUEST},
                {false, true, true, SC_OK},
        };
    }
}

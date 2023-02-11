package site.stellarburgers;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import site.stellarburgers.client.UserClient;
import site.stellarburgers.generator.UserGenerator;
import site.stellarburgers.pojo.RegisterUser;

import java.util.List;

import static org.apache.http.HttpStatus.*;
import static site.stellarburgers.generator.UserGenerator.UserField.*;

@RunWith(JUnitParamsRunner.class)
public class RegisterUserTest {

    private RegisterUser registerData;
    private String token = "";
    private int statusCode;
    private boolean isRegistered;

    @Test
    @DisplayName("Создание пользователя с валидными данными")
    public void registerUserWithValidData() {
        registerData = UserGenerator.getDefaultRegistrationData();
        ValidatableResponse responseRegister = UserClient.registerUser(registerData);

        token = responseRegister.extract().path("accessToken");
        statusCode = responseRegister.extract().statusCode();
        isRegistered = responseRegister.extract().path("success");

        ValidatableResponse responseDelete = UserClient.deleteUser(token);

        Assert.assertEquals("Ошибка в коде или теле ответа", List.of(SC_OK, true),
                List.of(statusCode, isRegistered));
    }

    @Test
    @DisplayName("Создание пользователя с занятым email-ом")
    public void registerDuplicateUser() {
        registerData = UserGenerator.getDefaultRegistrationData();
        ValidatableResponse responseRegister1 = UserClient.registerUser(registerData);
        ValidatableResponse responseRegister2 = UserClient.registerUser(registerData);

        token = responseRegister1.extract().path("accessToken");
        statusCode = responseRegister2.extract().statusCode();
        isRegistered = responseRegister2.extract().path("success");

        ValidatableResponse responseDelete = UserClient.deleteUser(token);

        Assert.assertEquals("Ошибка в коде или теле ответа", List.of(SC_FORBIDDEN, false),
                List.of(statusCode, isRegistered));
    }

    @Test
    @Parameters(method = "registerUserWithOneEmptyFieldParameters")
    @TestCaseName("Создание пользователя без {0}")
    public void registerUserWithOneEmptyField(UserGenerator.UserField emptyField) {
        registerData = UserGenerator.getRegistrationDataWithOneEmptyField(emptyField);
        ValidatableResponse responseRegister = UserClient.registerUser(registerData);

        statusCode = responseRegister.extract().statusCode();
        isRegistered = responseRegister.extract().path("success");

        Assert.assertEquals("Ошибка в коде или теле ответа", List.of(SC_FORBIDDEN, false),
                List.of(statusCode, isRegistered));
    }
    private Object[][] registerUserWithOneEmptyFieldParameters() {
        return new Object[][]{
                {EMAIL}, {PASSWORD}, {NAME},
        };
    }
}

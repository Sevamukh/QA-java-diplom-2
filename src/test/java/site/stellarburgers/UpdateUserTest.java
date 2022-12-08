package site.stellarburgers;

import io.restassured.response.ValidatableResponse;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import site.stellarburgers.client.UserClient;
import site.stellarburgers.generator.UserGenerator;
import site.stellarburgers.pojo.RegisterUser;

import java.util.List;

import static org.apache.http.HttpStatus.*;

@RunWith(JUnitParamsRunner.class)
public class UpdateUserTest {

    private final RegisterUser registerData = UserGenerator.getDefaultRegistrationData();

    private final RegisterUser updateData = UserGenerator.getDefaultUpdateData();
    private String token = "";
    private int statusCode;
    private boolean isUpdated;

    @Before
    public void setUp(){
        ValidatableResponse responseRegister = UserClient.registerUser(registerData);
        token = responseRegister.extract().path("accessToken");
    }

    @Test
    @Parameters(method = "updateUserWithAuthorizationParameters")
    @TestCaseName("Изменение данных пользователя с авторизацией: {0}")
    public void updateUserWithAuthorization(boolean isAuth, int status) {
        String token2 = "abc";
        if (isAuth) {token2 = token;}
        ValidatableResponse responseUpdate = UserClient.updateUser(updateData, token2);

        statusCode = responseUpdate.extract().statusCode();
        isUpdated = responseUpdate.extract().path("success");

        Assert.assertEquals("Ошибка в коде или теле ответа", List.of(status, isAuth),
                List.of(statusCode, isUpdated));
    }
    private Object[][] updateUserWithAuthorizationParameters() {
        return new Object[][]{
                {true, SC_OK},
                {false, SC_UNAUTHORIZED},
        };
    }

    @After
    public void tearDown(){
        ValidatableResponse responseDelete = UserClient.deleteUser(token);
    }
}

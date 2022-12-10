package site.stellarburgers.client;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Client {

    private final static String BASE_URL = "https://stellarburgers.nomoreparties.site/";

    protected static RequestSpecification getSpec() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(BASE_URL)
                .build();
    }

    protected static RequestSpecification getSpec(String bearerPlusToken) {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("authorization", bearerPlusToken)
                .setBaseUri(BASE_URL)
                .build();
    }
}

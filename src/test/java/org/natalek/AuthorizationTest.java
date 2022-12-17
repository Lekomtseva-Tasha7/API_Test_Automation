package org.natalek;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

public class AuthorizationTest extends AbstractTest {

    // POST https://test-stand.gb.ru/gateway/login

    @Test
    @DisplayName("Валидные значения логин/пароль.")
    void validLoginPasswordTest (){
        given()
                .body ("{\n"
                        + "\"username\": \"IvanIvanov-82\",\n"
                        + "\"password\": \"87826428f6\"\n"
                        + "}")
                .when()
                .post(getBaseUrlLogin())
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Невалидные значения логин/пароль.")
    void inValidLoginPasswordTest () {
        given()
                .body("{\n"
                        + "\"username\": \"IvanIvanov-82\",\n"
                        + "\"password\": \"87826428f6\"\n"
                        + "}")
                .when()
                .post(getBaseUrlLogin())
                .then()
                .statusCode(401);
    }

    @Test
    @DisplayName("Логин не может содержать спец. символы")
    void specialCharactersInLoginTest (){
        given()
                .body ("{\n"
                        + "\"username\": \"Tasha@\",\n"
                        + "\"password\": \"f2ccd46af7\"\n"
                        + "}")
                .when()
                .post(getBaseUrlLogin())
                .then()
                .statusCode(401);
    }

    @Test
    @DisplayName("Логин не может содержать более 20 символов")
    void loginIsLongerThan20charactersTest (){
        given()
                .body ("{\n"
                        + "\"username\": \"123456789123456789999\",\n"
                        + "\"password\": \"092b8e43ec\"\n"
                        + "}")
                .when()
                .post(getBaseUrlLogin())
                .then()
                .statusCode(401);
    }
}

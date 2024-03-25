package com.nikiforov.extensions;

import com.nikiforov.models.LoginModel;
import com.nikiforov.models.ResponseModel;
import io.qameta.allure.Step;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.nikiforov.specs.LoginSpecs.loginRequestSpec;
import static com.nikiforov.specs.LoginSpecs.loginResponseSpec;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class LoginExtension implements BeforeEachCallback {
    @Override
    @Step("API Authorization")
    public void beforeEach(ExtensionContext context) {
        LoginModel authData = new LoginModel();
        authData.setUserName("User21");
        authData.setPassword("User21$Test");

        ResponseModel response = given(loginRequestSpec)
                .contentType(JSON)
                .body(authData)
                .when()
                .post("/Account/v1/Login")
                .then()
                .spec(loginResponseSpec)
                .extract().as(ResponseModel.class);

        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID", response.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("token", response.getToken()));
        getWebDriver().manage().addCookie(new Cookie("expires", response.getExpires()));
    }
}

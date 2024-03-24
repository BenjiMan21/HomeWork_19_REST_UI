package com.nikiforov.tests;

import com.nikiforov.models.LoginModel;
import com.nikiforov.models.ResponseModel;
import com.nikiforov.pages.ProfilePage;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.nikiforov.specs.LoginSpecs.loginRequestSpec;
import static com.nikiforov.specs.LoginSpecs.loginResponseSpec;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class LoginTests extends TestBase {

    ProfilePage profilePage = new ProfilePage();


    @Test
    @DisplayName("Check list of books for empty value")
    @Owner("Nikiforov")
    @Tag("demoqa_tests")
    void loginAndCheckEmptyBookListTest() {
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

        step ("Open profile page", () -> {
            profilePage.openPage();
        });
        step ("Check profile name", () -> {
            profilePage.checkUserName("User21");
        });
        step ("Check list of books", () -> {
            profilePage.checkTable();
        });
    }
}

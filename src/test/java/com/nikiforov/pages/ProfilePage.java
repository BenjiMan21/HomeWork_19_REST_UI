package com.nikiforov.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ProfilePage {
    SelenideElement userNameCheck = $("#userName-value"),
            checkEmptyTable = $(".rt-noData");

    @Step("Open profile page")
    public ProfilePage openPage() {
        open("/profile");
        return this;
    }

    @Step("Check profile name")
    public ProfilePage checkUserName(String value) {
        userNameCheck.shouldHave(text(value));
        return this;
    }

    @Step("Check list of books")
    public ProfilePage checkTable () {
        checkEmptyTable.shouldBe(visible);
        return this;
    }
}

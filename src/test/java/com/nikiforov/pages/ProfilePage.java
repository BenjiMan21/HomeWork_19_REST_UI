package com.nikiforov.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ProfilePage {
    SelenideElement userNameCheck = $("#userName-value"),
            checkEmptyTable = $(".rt-noData");

    public ProfilePage openPage() {
        open("/profile");
        return this;
    }

    public ProfilePage checkUserName(String value) {
        userNameCheck.shouldHave(text(value));
        return this;
    }

    public ProfilePage checkTable () {
        checkEmptyTable.shouldBe(visible);
        return this;
    }
}

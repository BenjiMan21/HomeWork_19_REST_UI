package com.nikiforov.tests;

import com.nikiforov.extensions.WithLogin;
import com.nikiforov.pages.ProfilePage;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class LoginTests extends TestBase {

    ProfilePage profilePage = new ProfilePage();


    @Test
    @WithLogin
    @DisplayName("Check list of books for empty value")
    @Owner("Nikiforov")
    @Tag("demoqa_tests")
    void loginAndCheckEmptyBookListTest() {
            profilePage
                    .openPage()
                    .checkUserName("User21")
                    .checkTable();
    }
}

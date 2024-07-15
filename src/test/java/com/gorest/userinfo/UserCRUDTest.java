package com.gorest.userinfo;

import com.gorest.info.UserSteps;
import com.gorest.testbase.TestBase;
import com.gorest.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.annotations.Title;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class UserCRUDTest extends TestBase {

    static String name = TestUtils.generateName();
    static String email = TestUtils.generateEmail();
    static String gender = TestUtils.generateGender();
    static String status = TestUtils.generateStatus();

    static int userId;
    static String token = "a4805c7c5cda603b60345e721f166223ec510ce5c21e457586a3e20739d66d85";

    @Steps
    UserSteps userSteps;

    @Title("Create a new User")
    @Test
    public void test001() {
        ValidatableResponse response = userSteps.createUser(token, name, email, gender, status);
        response.log().all();
        response.statusCode(201);
        userId = response.extract().path("id");
    }

    @Title("Getting user detail")
    @Test
    public void test002() {
        ValidatableResponse response = userSteps.getUserInfoById(userId, token);
        response.statusCode(200);
    }

    @Title("Test003-Updating user info and verify updated details")
    @Test
    public void test003() {
        String uName = name + "updated";
        String uEmail = "u" + email;
        String uStatus = "inactive";

        ValidatableResponse response = userSteps.updateUserInfoById(userId, token, uName, uEmail, gender, uStatus);
        response.statusCode(200);
    }

    @Title("Test004-Deleting user and verify it deleted")
    @Test
    public void test004() {
        userSteps.deleteUser(userId, token).statusCode(204);
        userSteps.getUserInfoById(userId, token).statusCode(404);

    }

}

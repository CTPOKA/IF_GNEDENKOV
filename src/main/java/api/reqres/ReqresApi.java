package api.reqres;

import api.BaseApi;
import config.ConfigReader;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.ValidatableResponse;
import models.reqres.User;

import static io.restassured.RestAssured.given;

public class ReqresApi extends BaseApi {

    public ReqresApi() {
        super(ConfigReader.getProperty("reqres.api"));
    }

    public ValidatableResponse createUser(User user) {
        return given()
                .filter(new AllureRestAssured())
                .when()
                .body(user)
                .post("/users")
                .then();
    }
}
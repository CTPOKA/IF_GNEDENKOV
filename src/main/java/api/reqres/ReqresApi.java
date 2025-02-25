package api.reqres;

import io.restassured.response.ValidatableResponse;
import reqres.User;

import static io.restassured.RestAssured.given;

public class ReqresApi extends BaseReqresApi {

    public ValidatableResponse createUser(User user) {
        return given()
                .when()
                .body(user)
                .post("/users")
                .then();
    }
}
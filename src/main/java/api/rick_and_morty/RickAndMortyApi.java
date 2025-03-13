package api.rick_and_morty;

import api.BaseApi;
import config.ConfigReader;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

import io.qameta.allure.restassured.AllureRestAssured;

public class RickAndMortyApi extends BaseApi {

    public RickAndMortyApi() {
        super(ConfigReader.getProperty("rick.and.morty.api"));
    }

    public ValidatableResponse getCharacters() {
        return given()
                .filter(new AllureRestAssured())
                .when()
                .get("/character")
                .then();
    }

    public ValidatableResponse getEpisodeById(String id) {
        return given()
                .filter(new AllureRestAssured())
                .when()
                .get("/episode/" + id)
                .then();
    }

    public ValidatableResponse getCharacterById(String id) {
        return given()
                .filter(new AllureRestAssured())
                .when()
                .get("/character/" + id)
                .then();
    }
}

package api.rick_and_morty;

import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class RickAndMortyApi extends BaseRickAndMortyApi {

    public ValidatableResponse getCharacters() {
        return given()
                .when()
                .get("/character")
                .then();
    }

    public ValidatableResponse getEpisodeById(String id) {
        return given()
                .when()
                .get( "/episode/" + id)
                .then();
    }

    public ValidatableResponse getCharacterById(String id) {
        return given()
                .when()
                .get( "/character/" + id)
                .then();
    }
}
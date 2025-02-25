package api.rick_and_morty;

import api.Specifications;
import config.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;

public abstract class BaseRickAndMortyApi {
    public BaseRickAndMortyApi() {

        RestAssured.requestSpecification = Specifications.baseRequestSpec(ConfigReader.getProperty("rickandmorty.api"));
        RestAssured.responseSpecification = new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .build();
    }
}
package api.reqres;

import api.Specifications;
import config.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;

public abstract class BaseReqresApi {
    public BaseReqresApi() {
        RestAssured.requestSpecification = Specifications.baseRequestSpec(ConfigReader.getProperty("reqres.api"));
        RestAssured.responseSpecification = new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .build();
    }
}
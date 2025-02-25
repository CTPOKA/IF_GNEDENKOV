package steps;

import api.reqres.ReqresApi;
import reqres.User;
import org.apache.http.HttpStatus;
import utils.MapperUtils;

public class ReqresSteps {

    private static final ReqresApi reqresApi = new ReqresApi();

    public User getUserFromFile(String filePath) {
        return MapperUtils.readFromFile(filePath, User.class);
    }

    public User createUser(User user) {
        return reqresApi.createUser(user)
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .body()
                .as(User.class);
    }
}
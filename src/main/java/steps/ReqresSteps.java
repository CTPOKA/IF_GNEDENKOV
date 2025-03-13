package steps;

import api.reqres.ReqresApi;
import io.cucumber.java.ru.*;
import models.reqres.User;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import utils.MapperUtils;

public class ReqresSteps {
    private static final ReqresApi reqresApi = new ReqresApi();
    private User user;
    private User createdUser;

    @Когда("сформируем пользователя c данными из файла {string}")
    public void getUserFromFile(String filePath) {
        user = MapperUtils.readFromFile(filePath, User.class);
    }

    @Когда("сменим пользователю имя на {string} и работу на {string}")
    public void updateUserDetails(String name, String job) {
        user.setName(name);
        user.setJob(job);
    }

    @Когда("отправим запрос на создание этого пользователя в reqres")
    public void createUser() {
        createdUser = reqresApi.createUser(user)
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .body()
                .as(User.class);
    }

    @Тогда("новый заданный пользователь успешно создан")
    public void verifyUserCreated() {
        Assertions.assertNotNull(createdUser, "Созданный пользователь равен null");
        Assertions.assertNotNull(createdUser.getId(), "ID созданного пользователя отсутствует");
        Assertions.assertEquals(user.getName(), createdUser.getName(), "Имена не совпадают");
        Assertions.assertEquals(user.getJob(), createdUser.getJob(), "Работа не совпадает");
    }
}

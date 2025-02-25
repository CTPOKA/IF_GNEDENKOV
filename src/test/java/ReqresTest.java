import config.ConfigReader;
import reqres.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.ReqresSteps;

public class ReqresTest {

    ReqresSteps reqresSteps = new ReqresSteps();

    @Test
    @DisplayName("Проверка создания пользователя")
    public void checkUserCreation() {
        User user = reqresSteps.getUserFromFile("src/test/resources/json/user.json");
        user.setName(ConfigReader.getProperty("reqres.user.name"));
        user.setJob(ConfigReader.getProperty("reqres.user.job"));
        User createdUser = reqresSteps.createUser(user);

        Assertions.assertEquals(user.getName(), createdUser.getName());
        Assertions.assertEquals(user.getJob(), createdUser.getJob());
    }
}
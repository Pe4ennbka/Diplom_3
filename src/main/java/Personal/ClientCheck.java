package Personal;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;

import java.net.HttpURLConnection;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class ClientCheck {
    @Step("Проверка на создения клиета (200) и получении статуса  ok: 200")
    public String createdClient(ValidatableResponse response, Client client) {
        String accessToken = response
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .body("success", equalTo(true))
                .body("user.email", equalTo(client.getEmail()))
                .body("user.name", equalTo(client.getName()))
                .body("accessToken", notNullValue())
                .body("refreshToken", notNullValue())
                .extract()
                .path("accessToken");
        ;
        return accessToken;

    }
    @Step("Проверка на получение id (200) и получении id и номер")
    public String loginCourier(ValidatableResponse response) {
        String accessToken = response
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .extract()
                .path("accessToken");
        ;
        return accessToken;
    }
    @Step("Проверка на удаления клиента и полчуения 202 ответа")
    public void deleteClient(ValidatableResponse response) {
        boolean success = response
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_ACCEPTED)
                .body("message", equalTo("User successfully removed"))
                .extract()
                .path("success");
        ;
        Assert.assertTrue(success);
    }
}

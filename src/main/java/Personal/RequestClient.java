package Personal;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class RequestClient {
    @Step("Вызвать метод POST в создании клиента /api/auth/register")
    public ValidatableResponse createClient(Client client){
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri("https://stellarburgers.nomoreparties.site")
                .body(client)
                .when()
                .post("/api/auth/register")
                .then().log().all();
    }
    @Step("Вызвать метод POST в залогинивании клиента /api/v1/courier/login")
    public  ValidatableResponse loginClient(LoginClient client){
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri("https://stellarburgers.nomoreparties.site")
                .body(client)
                .when()
                .post("/api/auth/login")
                .then().log().all();
    }
    @Step("Вызвать метод DELETE у клиента /api/auth/user")
    public  ValidatableResponse delete(String accessToken){
        return given().log().all()
                .header("Authorization", accessToken)
                .contentType(ContentType.JSON)
                .baseUri("https://stellarburgers.nomoreparties.site")
                .when()
                .delete("/api/auth/user")
                .then().log().all();
    }
}

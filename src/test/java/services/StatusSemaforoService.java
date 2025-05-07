package services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.StatusSemaforoModel;

import static io.restassured.RestAssured.given;

public class StatusSemaforoService {

    final StatusSemaforoModel statusSemaforoModel = new StatusSemaforoModel();
    public final Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();
    public Response response;
    String baseUrl = "http://localhost:8080";


    public void setStatusSemaforo(String status, String value) {
        switch (status) {
            case "id" -> statusSemaforoModel.setId(Long.valueOf(value));
            case "descricao" -> statusSemaforoModel.setDescricao(value);
            default -> throw new IllegalStateException("Unexpected status" + status);
        }
    }

    public void createStatus(String endPoint) {
        String url = baseUrl + endPoint;
        String bodyToSend = gson.toJson(statusSemaforoModel);
        response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(bodyToSend)
                .when()
                .post(url)
                .then()
                .extract()
                .response();
    }
}
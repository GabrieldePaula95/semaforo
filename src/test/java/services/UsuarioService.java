package services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.StatusSemaforoModel;
import model.UsuarioModel;

import static io.restassured.RestAssured.given;

public class UsuarioService {
    final UsuarioModel usuarioModel = new UsuarioModel();
    public final Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();
    public Response response;
    String baseUrl = "http://localhost:8080";

    public void setUsuario(String usuarios, String value) {
        switch (usuarios) {
            case "id" -> usuarioModel.setId(Long.valueOf(value));
            case "nome" -> usuarioModel.setNome(value);
            case "email" -> usuarioModel.setEmail(value);
            case "senha" -> usuarioModel.setSenha(value);
            case "papel" -> usuarioModel.setPapel(value);
            default -> throw new IllegalStateException("Unexpected user" + usuarios);
        }
    }

    public void updateUsuario(String endpoint) {
        String url = baseUrl + endpoint;
        String json = gson.toJson(usuarioModel);
        response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(json)
                .when()
                .put(url)
                .then()
                .extract()
                .response();
    }

    public void createUsuario(String endpoint) {
        String url = baseUrl + endpoint;
        String json = gson.toJson(usuarioModel);
        response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(json)
                .when()
                .post(url)
                .then()
                .extract()
                .response();
    }
}

package steps;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import services.StatusSemaforoService;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class StatusSemaforoSteps {

    private StatusSemaforoService statusSemaforoService = new StatusSemaforoService();
    private RequisicaoBuilder requisicaoBuilder;
    private ResponseEntity<String> resposta;
    private Long statusId;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Dado("que o sistema exige autenticação básica para os endpoints protegidos")
    public void queOSistemaExigeAutenticacaoBasicaParaOsEndpointsProtegidos() {
    }

    @Dado("que eu tenha um token de autenticação válido")
    public void queEuTenhaUmTokenDeAutenticaçãoVálido() {
        requisicaoBuilder = new RequisicaoBuilder("user", "test123");
    }

    @E("que eu tenha os seguintes dados do status do semáforo:")
    public void queEuTenhaOsSeguintesDadosDoStatusDoSemáforo(List<Map<String, String>> rows) {
        for (Map<String, String> columns : rows) {
            statusSemaforoService.setStatusSemaforo(columns.get("campo"), columns.get("valor"));
        }
    }

    @Então("o status code da criação deve ser {int}")
    public void oStatusCodeDaCriacaoDeveSer(int statusCode) {
        Assert.assertEquals(statusCode, statusSemaforoService.response.statusCode());
    }

    @Dado("que exista um status de semáforo com os seguintes dados:")
    public void queExistaUmStatusDeSemáforoComOsSeguintesDados(List<Map<String, String>> rows) throws Exception{
        requisicaoBuilder = new RequisicaoBuilder("user", "test123");

        for (Map<String, String> columns : rows) {
            statusSemaforoService.setStatusSemaforo(columns.get("campo"), columns.get("valor"));
        }

        statusSemaforoService.createStatus("/status-semaforo");

        assertEquals(200, statusSemaforoService.response.statusCode());

        JsonNode json = new ObjectMapper().readTree(statusSemaforoService.response.body().asString());
        statusId = json.get("id").asLong();
    }

    @Então("o status code da consulta deve ser {int}")
    public void oStatusCodeDaConsultaDeveSer(int statusEsperado) {
        assertEquals(statusEsperado, statusSemaforoService.response.statusCode());
    }

    @E("a resposta deve conter a descrição {string}")
    public void aRespostaDeveConterDescricao(String descricaoEsperada) throws Exception {
        JsonNode json = objectMapper.readTree(statusSemaforoService.response.getBody().asString());
        String descricao = json.get("descricao").asText();
        assertEquals(descricaoEsperada, descricao);
    }

    @Dado("que eu tenha os dados abaixo do status do semáforo:")
    public void queEuTenhaOsDadosAbaixoDoStatusDoSemaforo(List<Map<String, String>> rows) {
        for (Map<String, String> columns : rows) {
            statusSemaforoService.setStatusSemaforo(columns.get("campo"), columns.get("valor"));
        }
    }
    @Quando("eu enviar a requisição para o endpoint {string} de cadastro de status")
    public void euEnviarARequisiçãoParaOEndpointDeCadastroDeStatus(String endPoint) {
        statusSemaforoService.createStatus(endPoint);
    }

    @Então("o status code deve ser {int}")
    public void oStatusCodeDeveSer(int statusCode) {
        assertEquals(statusCode, statusSemaforoService.response.statusCode());
    }

}
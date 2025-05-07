package steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.networknt.schema.ValidationMessage;
import io.cucumber.java.pt.*;
import org.junit.Assert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.context.ActiveProfiles;
import services.UsuarioService;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class UsuarioSteps {

    @LocalServerPort
    private int port;

    private ResponseEntity<String> resposta;
    private Long usuarioId;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private UsuarioService usuarioService = new UsuarioService();

    private RequisicaoBuilder requisicaoBuilder;

    @Dado("que eu tenha cadastrado um usuário com os seguintes dados:")
    public void cadastrarUsuario(List<Map<String, String>> rows) throws Exception {
        requisicaoBuilder = new RequisicaoBuilder("user", "test123");

        for (Map<String, String> columns : rows) {
            usuarioService.setUsuario(columns.get("campo"), columns.get("valor"));
        }

        usuarioService.createUsuario("/usuarios");

        assertEquals(200, usuarioService.response.statusCode());

        JsonNode json = new ObjectMapper().readTree(usuarioService.response.body().asString());
        usuarioId = json.get("id").asLong();
    }


    @Quando("eu atualizar esse usuário com o nome {string} e papel {string}")
    public void atualizarUsuario(String novoNome, String novoPapel) {
        usuarioService.setUsuario("nome", novoNome);
        usuarioService.setUsuario("papel", novoPapel);
        usuarioService.setUsuario("email", "joao@exemplo.com");
        usuarioService.setUsuario("senha", "123456");

        usuarioService.updateUsuario("/usuarios/" + usuarioId);

        resposta = ResponseEntity
                .status(usuarioService.response.statusCode())
                .body(usuarioService.response.getBody().asString());
    }

    @Então("o status code da resposta deve ser {int}")
    public void validarStatusCode(int esperado) {
        assertEquals(esperado, resposta.getStatusCodeValue());
    }

    @E("a resposta deve conter o nome {string} e papel {string}")
    public void validarNomeEPapel(String nome, String papel) throws Exception {
        JsonNode json = objectMapper.readTree(resposta.getBody());
        assertEquals(nome, json.get("nome").asText());
        assertEquals(papel, json.get("papel").asText());
    }

    @Dado("que eu tenha uma autenticação válida")
    public void queEuTenhaUmaAutenticaçãoVálida() {
        requisicaoBuilder = new RequisicaoBuilder("user", "test123");
    }

    @E("que eu tente atualizar um usuário com os seguintes dados:")
    public void preencherDadosUsuario(List<Map<String, String>> rows) {
        for (Map<String, String> linha : rows) {
            usuarioService.setUsuario(linha.get("campo"), linha.get("valor"));
        }
    }

    @Dado("que eu tenha os seguintes dados:")
    public void queEuTenhaOsSeguintesDados(List<Map<String, String>> rows) throws Exception {
        requisicaoBuilder = new RequisicaoBuilder("user", "test123");

        for (Map<String, String> columns : rows) {
            usuarioService.setUsuario(columns.get("campo"), columns.get("valor"));
        }
    }

    @Quando("eu enviar a requisição para o endpoint {string} de cadastro de entregas")
    public void euEnviarARequisicaoParaOEndpointDeCadastroDeEntregas(String enpoint) {
        usuarioService.createUsuario("/usuarios");
    }

    @E("que o arquivo de contrato esperado é o {string}")
    public void queOArquivoDeContratoEsperadoEO(String contract) throws IOException {
        usuarioService.setContract(contract);
    }

    @Então("a resposta da requisição deve estar em conformidade com o contrato selecionado")
    public void aRespostaDaRequisicaoDeveEstarEmConformidadeComOContratoSelecionado() throws IOException {
        Set<ValidationMessage> validateResponse = usuarioService.validateResponseAgainstSchema();
        Assert.assertTrue("O contrato está inválido. Erros encontrados: " + validateResponse, validateResponse.isEmpty());
    }
}

package med.voll.api.Vollmed.Api.controller;

import med.voll.api.Vollmed.Api.domain.consulta.AgendaDeConsultas;
import med.voll.api.Vollmed.Api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.Vollmed.Api.domain.consulta.DadosDetalhamentoConsulta;
import med.voll.api.Vollmed.Api.domain.medico.Especialidade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@AutoConfigureJsonTesters
class ConsultaControllerTest {

    // Simulando um mock para requisição, fazendo um teste de unidade de maneira isolada
    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DadosAgendamentoConsulta> dadosAgendamentoConsultaJson; // O que envia os dados

    @Autowired
    private JacksonTester<DadosDetalhamentoConsulta> dadosDetalhamentoConsultaJson; // O que recebe os dados

    @MockitoBean
    private AgendaDeConsultas agendaDeConsultas;


    @Test
    @DisplayName("Deveria devolver codigo http 400 quando informações estão inválidas")
    @WithMockUser // faz um mock que usuário para simular um usuário logado
    void agendarCenario1() throws Exception {
        // Dispara uma requisição post para a rota /agendar sem levar nenhum corpo
        var response = mvc.perform(post("/consultas")) // precisa ter a mesma url configurada no controller
                .andReturn().getResponse();

        // Verifica se o status que está na variável response é 400
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver codigo http 200 quando informações estão válidas")
    @WithMockUser
    void agendarCenario2() throws Exception {

        var data = LocalDateTime.now().plusHours(2);
        var especialidade = Especialidade.GINECOLOGIA;

        var dadosDetalhamento = new DadosDetalhamentoConsulta(null, 2l, 5l, data);

        when(agendaDeConsultas.agendar(any())).thenReturn(dadosDetalhamento);

        var response = mvc
                .perform(post("/consultas")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(dadosAgendamentoConsultaJson.write(
                                    new DadosAgendamentoConsulta(2l, 5l, data, especialidade)
                            ).getJson())
                        )
                        .andReturn()
                        .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonEsperado = dadosDetalhamentoConsultaJson.write(
                    dadosDetalhamento
        ).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }


}
package med.voll.api.Vollmed.Api.domain.consulta.validacoes.agendamento;

import med.voll.api.Vollmed.Api.domain.consulta.DadosAgendamentoConsulta;

public interface ValidadorAgendamentoDeConsulta {

    void validar(DadosAgendamentoConsulta dados);
}

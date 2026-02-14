package med.voll.api.Vollmed.Api.domain.consulta.validacoes.cancelamento;

import med.voll.api.Vollmed.Api.domain.consulta.DadosCancelamentoConsulta;

public interface ValidadorCancelamentoDeConsulta {

    void validar(DadosCancelamentoConsulta dados);
}

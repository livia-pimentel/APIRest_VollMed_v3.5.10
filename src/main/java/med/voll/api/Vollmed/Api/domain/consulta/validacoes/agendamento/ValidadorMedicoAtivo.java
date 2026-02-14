package med.voll.api.Vollmed.Api.domain.consulta.validacoes.agendamento;

import med.voll.api.Vollmed.Api.domain.ValidacaoException;
import med.voll.api.Vollmed.Api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.Vollmed.Api.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentoDeConsulta{

    // Atributo
    @Autowired
    private MedicoRepository repository;

    // Metodo
    public void validar(DadosAgendamentoConsulta dados) {
        //Escolha do médico opcional
        if (dados.idMedico() == null) {
            return;
        }

        var medicoEstaAtivo = repository.findAtivoById(dados.idMedico());
        if (!medicoEstaAtivo) {
            throw new ValidacaoException("Consulta não pode ser agendada com médico excluído");
        }
    }


}

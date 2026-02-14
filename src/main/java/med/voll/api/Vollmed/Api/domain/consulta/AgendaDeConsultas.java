package med.voll.api.Vollmed.Api.domain.consulta;

import med.voll.api.Vollmed.Api.domain.ValidacaoException;
import med.voll.api.Vollmed.Api.domain.consulta.validacoes.agendamento.ValidadorAgendamentoDeConsulta;
import med.voll.api.Vollmed.Api.domain.consulta.validacoes.cancelamento.ValidadorCancelamentoDeConsulta;
import med.voll.api.Vollmed.Api.domain.medico.Medico;
import med.voll.api.Vollmed.Api.domain.medico.MedicoRepository;
import med.voll.api.Vollmed.Api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeConsultas {
    // Atributos
    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    // Usa o List para pegar a interface e o spring automaticamente pega todas as classes que implementam essa interface
    @Autowired
    private List<ValidadorAgendamentoDeConsulta> validadores;

    @Autowired
    private List<ValidadorCancelamentoDeConsulta> validadoresCancelamento;

    // Metodos
    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados) {

        // Se não existe o id, interrompe a aplicação
        if (!pacienteRepository.existsById(dados.idPaciente())) {
            throw new ValidacaoException("Id do paciente informado não existe!");
        }

        if (dados.idMedico() != null &&!medicoRepository.existsById(dados.idMedico())) {
            throw new ValidacaoException("Id do médico informado não existe!");
        }

        validadores.forEach(v -> v.validar(dados));

        // Recupera os dados medico e paciente por id
        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        var medico = esccolherMedico(dados);

        if(medico == null) {
            throw new ValidacaoException("Não existe médico disponivel nessa data!");
        }

        var consulta = new Consulta(null, medico, paciente, dados.data(), null);
        consultaRepository.save(consulta);

        return new DadosDetalhamentoConsulta(consulta);
    }

    public void cancelar(DadosCancelamentoConsulta dados) {
        if (!consultaRepository.existsById(dados.idConsulta())) {
            throw new ValidacaoException("Id da consulta informado não existe");
        }

        validadoresCancelamento.forEach(v -> v.validar(dados));

        var consulta = consultaRepository.getReferenceById(dados.idConsulta());
        consulta.cancelar(dados.motivo());
    }

    private Medico esccolherMedico(DadosAgendamentoConsulta dados) {
        // Se o id do médico não for nulo, carrega os dados do banco
        if (dados.idMedico() != null) {
            return medicoRepository.getReferenceById(dados.idMedico());
        }

        // Se o id do médico for nulo, verifica se a especialidade do médico foi preenchida
        if (dados.especialidade() == null) {
            throw new ValidacaoException("Especialidade é obrigatória, quando o médico não for escolhido");
        }

        // Seleciona médico da especialidade desejada de forma aleatória
        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());

    }

}


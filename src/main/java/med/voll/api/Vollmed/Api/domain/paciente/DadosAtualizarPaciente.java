package med.voll.api.Vollmed.Api.domain.paciente;

import jakarta.validation.constraints.NotNull;
import med.voll.api.Vollmed.Api.domain.endereco.DadosEndereco;

public record DadosAtualizarPaciente(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}

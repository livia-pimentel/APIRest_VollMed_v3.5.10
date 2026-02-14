package med.voll.api.Vollmed.Api.domain.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.api.Vollmed.Api.domain.endereco.DadosEndereco;

public record DadosAtualizacaoMedico(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}

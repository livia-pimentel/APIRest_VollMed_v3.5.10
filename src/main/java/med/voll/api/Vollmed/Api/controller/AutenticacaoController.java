package med.voll.api.Vollmed.Api.controller;

import jakarta.validation.Valid;
import med.voll.api.Vollmed.Api.domain.usuario.DadosAutenticacao;
import med.voll.api.Vollmed.Api.domain.usuario.Usuario;
import med.voll.api.Vollmed.Api.infra.security.TokenService;
import med.voll.api.Vollmed.Api.security.DadosTokenJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    // Atributos
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    // Metodo
    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
        var authenticationTokennttoken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authentication = manager.authenticate(authenticationTokennttoken);
        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }

}

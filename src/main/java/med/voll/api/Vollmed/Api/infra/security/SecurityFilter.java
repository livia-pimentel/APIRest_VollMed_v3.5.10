package med.voll.api.Vollmed.Api.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import med.voll.api.Vollmed.Api.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
// Essa classe faz o filtro apenas 1 vez por requisição
public class SecurityFilter extends OncePerRequestFilter {

    // Atributos
    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository repository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var tokenJWT = recuperarToken(request);

        if (tokenJWT != null) {
            // Pega o subject(usuario) associado ao token
            var subject = tokenService.getSubject(tokenJWT);

            // Recupera o usuário
            var usuario = repository.findByLogin(subject);

            // Força a autenticação do usário, pois a aplicação é Stateless
            var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // Chama o próximo filtro
        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {

        var authorizationHeader = request.getHeader("Authorization");
        // Verificar se está vindo o token no cabeçalho
        if (authorizationHeader != null) {
            // Retorna o token sem o prefixo (Bearer)
            return authorizationHeader.replace("Bearer ", "");
        }

        return null;
    }
}


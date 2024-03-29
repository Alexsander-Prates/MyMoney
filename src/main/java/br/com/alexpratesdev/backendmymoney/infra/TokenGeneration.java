package br.com.alexpratesdev.backendmymoney.infra;

import br.com.alexpratesdev.backendmymoney.entity.UsuarioEntity;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenGeneration {
    @Value("${api.sercurity.token.secret")
    public String secret;

    public Algorithm criarAlgoritimoSecreto(){
        Algorithm algorithm = Algorithm.HMAC256(this.secret);
        return algorithm;
    }
    public String gerarToken(UsuarioEntity usuarioEntity) throws Exception {
        try {
            Algorithm algorithm = criarAlgoritimoSecreto();
            String token = JWT.create()
                    .withIssuer("auth-aplication") //nome aplicação
                    .withSubject(usuarioEntity.getLogin()) //quem recebe o token
                    .withExpiresAt(getExpirationDate()) //time
                    .sign(algorithm); //criador do token
            return token;
        } catch (JWTCreationException exception) {
            throw new Exception("Erro criação de token");
        }

    }

    public String validarToken(String token){
        try{

            Algorithm algorithm = criarAlgoritimoSecreto();
            return JWT.require(algorithm)
                    .withIssuer("auth-aplication") //nome aplicação
                    .build()
                    .verify(token)
                    .getSubject();

        }catch (JWTCreationException exception) {
            return ""; //retornar String vazia para não ter autorização
        }
    }
    private Instant getExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}

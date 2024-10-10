package br.com.matheus.securityJwt;

import br.com.matheus.data.vo.v1.security.TokenVO;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
public class JwtTokenProvider {

    @Value("${security.jwt.token.secret-key:secret}")
    private String secretKey = "secret";

    @Value("${security.jwt.token.expire-lenght:3600000}")
    private long validInMiliSeconds = 3600000; //1hora

    @Autowired
    private UserDetailsService userDetailsService;

    Algorithm algorithm = null;

    @PostConstruct //Ele só é chamado dps de inicializar tudo mas antes do sistema executar alguma ação do usuário
    protected void init(){
        //Encriptando a secret
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        algorithm = Algorithm.HMAC256(secretKey.getBytes());
    }

    public TokenVO createAccessToken(String username, List<String> roles){
        Date now = new Date();
        Date valid = new Date(now.getTime() + validInMiliSeconds);
        var accessToken = getAccessToken(username, roles, now, valid);
        var refreshToken = refreshAccessToken(username, roles, now);
        return new TokenVO(username, true, now, valid, accessToken, refreshToken);
    }

    private String refreshAccessToken(String username, List<String> roles, Date now) {
    }

    private String getAccessToken(String username, List<String> roles, Date now, Date valid) {
    }
}

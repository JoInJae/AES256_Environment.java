package app.utility;

import app.data.type.Token;
import app.data.environment.JWT_Environment;

import app.data.response.type.Response;
import app.exception.InvalidAuthorizationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWT {

    private final JWT_Environment environment;

    public JWT(JWT_Environment environment) {
        this.environment = environment;
    }

    public String create(Token type, String subject, String uuid){

        Map<String, Object> claims = new HashMap<>(){
            private static final long serialVersionUID = 8812712777761754913L;
            {
                put("type", type);
                put("subject", subject);
                put("uuid", uuid);
            }
        };

        long expired_time = System.currentTimeMillis() + type.getExpiration();

        return Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(Base64.getDecoder().decode(environment.getPrivate_key())), SignatureAlgorithm.HS256)
                .setClaims(claims).setExpiration(new Date(expired_time)).compact();

    }

    public Claims get(String input){

        try{

            return Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(Base64.getDecoder().decode(environment.getPrivate_key()))).build()
                    .parseClaimsJws(input).getBody();

        }catch (ExpiredJwtException e){

            throw new InvalidAuthorizationException(Response.FAIL_TOKEN_TIMEOUT);

        }catch (Exception e){

            throw new InvalidAuthorizationException(Response.FAIL_TOKEN_INVALID);

        }

    }

}

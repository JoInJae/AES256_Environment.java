package app.utility;

import app.data.request.type.Token;
import app.data.environment.JWT_Environment;

import app.exception.InvalidAuthorizationException;
import io.jsonwebtoken.Claims;
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

    public String create(Token type, String uuid){

        Map<String, String> claims = new HashMap<>(){
            private static final long serialVersionUID = 8812712777761754913L;
            {
                put("uuid", uuid);
                put("type", type.name());
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

        }catch (Exception e){

            throw new InvalidAuthorizationException();

        }

    }

}

package app.interceptor;

import app.data.entity.type.Token;
import app.data.entity.user.User_Account;
import app.exception.InvalidAuthorizationException;
import app.repository.user.User_Repository;
import app.utility.JWT;

import io.jsonwebtoken.Claims;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Date;
import java.util.Optional;

@Component
public class Authentication_Interceptor implements HandlerInterceptor {

    private final User_Repository user_repository;
    private final JWT jwt;

    public Authentication_Interceptor(User_Repository user_repository, JWT jwt) {
        this.user_repository = user_repository;
        this.jwt = jwt;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) {

        String authorization = request.getHeader("Authorization");

        if(authorization == null || !(authorization.startsWith("Bearer "))) throw new InvalidAuthorizationException();

        String token = authorization.replace("Bearer ","");

        Claims claims = jwt.get(token);

        if(claims.getExpiration().before(new Date())) throw new InvalidAuthorizationException();

        String uuid = (String)claims.get("uuid");

        Optional<User_Account> is_user_account = user_repository.user_account_get(uuid);

        if(is_user_account.isEmpty()) throw new InvalidAuthorizationException();

        switch (Token.valueOf((String)claims.get("type"))){
            case ACCESS:
                    request.setAttribute("uuid", uuid);
                    return true;
            case REFRESH:
                    if(is_user_account.get().getRefresh().equals(token)){
                        request.setAttribute("access", jwt.create(Token.ACCESS, uuid));
                        return true;
                    }else{
                        throw new InvalidAuthorizationException();
                    }
            default: throw new InvalidAuthorizationException();
        }

    }

}

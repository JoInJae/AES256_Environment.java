package app.config.interceptor;

import app.data.response.type.Response;
import app.data.type.Token;
import app.config.exception.InvalidAuthorizationException;
import app.mvc.repository.Admin_Custom_Repository;
import app.mvc.repository.User_Custom_Repository;
import app.utility.JWT;
import io.jsonwebtoken.Claims;
import lombok.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Component
public class Authentication implements HandlerInterceptor {

    private final Admin_Custom_Repository admin_repository;
    private final User_Custom_Repository user_repository;
    private final JWT jwt;

    public Authentication(Admin_Custom_Repository admin_repository, User_Custom_Repository user_repository, JWT jwt) {
        this.admin_repository = admin_repository;
        this.user_repository = user_repository;
        this.jwt = jwt;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) {

        String authorization = request.getHeader("Authorization");

        if(authorization == null || !(authorization.startsWith("Bearer "))) throw new InvalidAuthorizationException(Response.FAIL_TOKEN_NOT_EXIST);

        String token = authorization.replace("Bearer ","");

        Claims claims = jwt.get(token);

        Token type = Token.valueOf((String)claims.get("type"));
        String uuid = (String)claims.get("uuid");
        String subject = (String)claims.get("subject");

        Optional<?> is_account = Optional.empty();

        if(subject.equals("user")){

            is_account = user_repository.user_account_get(uuid);



        }else if(subject.equals("admin")){

            is_account = admin_repository.account_get_by_uuid(uuid);

        }

        if(type == Token.ACCESS){

            if(is_account.isEmpty()) throw new InvalidAuthorizationException(Response.FAIL_TOKEN_INVALID);

            request.setAttribute("uuid", uuid);

        }else if(type == Token.REFRESH){

            request.setAttribute("access", jwt.create(Token.ACCESS, subject, uuid));

        }else{

            throw new InvalidAuthorizationException(Response.FAIL_TOKEN_INVALID);

        }

        return true;

    }

}

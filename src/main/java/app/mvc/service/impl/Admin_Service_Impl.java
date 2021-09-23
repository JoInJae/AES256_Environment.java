package app.mvc.service.impl;

import app.data.entity.part.admin.Admin_Account;
import app.data.request.AdminDTO;
import app.data.response.Message;
import app.data.response.MessageB;
import app.data.response.type.Response;
import app.data.type.Token;
import app.config.exception.InvalidAuthorizationException;
import app.mvc.repository.Admin_Custom_Repository;
import app.mvc.service.Admin_Service;
import app.mvc.service.basement.Base_Service;
import app.utility.JWT;
import org.springframework.stereotype.Service;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class Admin_Service_Impl extends Base_Service<Admin_Custom_Repository> implements Admin_Service{

    private final JWT jwt;

    public Admin_Service_Impl(Admin_Custom_Repository admin_custom_repository, JWT jwt) {
        super(admin_custom_repository);
        this.jwt = jwt;
    }

    @Transactional
    @Override
    public Message admin_put(AdminDTO.Input param) {

        Optional<Admin_Account>is_account = repository.account_get_by_id(param.getId());

        if(is_account.isPresent()) throw new InvalidAuthorizationException(Response.FAIL_ID_DUPLICATE);

        Admin_Account admin_account = param.to_entity();

        em.persist(admin_account);

        return Message.ok();

    }

    @Transactional
    @Override
    public Message admin_login(AdminDTO.Login_Check param, HttpServletResponse response) {

        Optional<Admin_Account>is_account = repository.account_get_by_id(param.getId());

        if(is_account.isEmpty()) throw new InvalidAuthorizationException(Response.FAIL_ID);

        Admin_Account admin_account = is_account.get();

        if(!admin_account.getPassword().match(param.getPassword()))throw new InvalidAuthorizationException(Response.FAIL_PASSWORD);

        String uuid = admin_account.getAdmin().getUuid();

        String access = jwt.create(Token.ACCESS, "admin", uuid);
        String refresh = jwt.create(Token.REFRESH, "admin", uuid);

        if(repository.token_update(admin_account, refresh) <= 0) throw new InvalidAuthorizationException(Response.ERROR_SQL);

        Cookie cookie = new Cookie("refresh", refresh);

        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setDomain("super-brain.co.kr");
        cookie.setPath("/");
        cookie.setMaxAge((int)(Token.REFRESH.getExpiration() / 1000));

        response.addCookie(cookie);

        return MessageB.ok(AdminDTO.Login_Result.builder().access(access).build());

    }

    @Override
    public Message admin_reissue(String refresh) {

        String uuid = jwt.get(refresh).get("uuid").toString();

        return MessageB.ok(AdminDTO.Login_Result.builder().access(jwt.create(Token.ACCESS, "admin", uuid)).build());

    }


}

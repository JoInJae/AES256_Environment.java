package app.mvc.controller;

import app.data.request.AdminDTO;
import app.data.response.Message;
import app.data.response.MessageB;
import app.mvc.controller.basement.Base_Controller;
import app.mvc.service.Admin_Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/admin", method = RequestMethod.POST)
@RestController
public class Admin_Controller extends Base_Controller<Admin_Service> {

    protected Admin_Controller(Admin_Service service) {
        super(service);
    }

    @PostMapping("/put")
    public ResponseEntity<Message> user_put(@RequestBody AdminDTO.Input param){

        return ResponseEntity.ok(service.admin_put(param));

    }

    @PostMapping("/login")
    public ResponseEntity<Message> user_login(@RequestBody AdminDTO.Login_Check param, HttpServletResponse response){

        return ResponseEntity.ok(service.admin_login(param, response));

    }

    @PostMapping("/reissue")
    public ResponseEntity<Message> user_reissue(@CookieValue(value="refresh") Cookie cookie){

        return ResponseEntity.ok(service.admin_reissue(cookie.getValue()));

    }

    @PostMapping("/get/main/info")
    public ResponseEntity<Message> main_info_get(){

        String[][] data = new String[][]{
                {"남성 회원", "50"},
                {"여성 회원", "50"}
        };

        return ResponseEntity.ok(MessageB.ok(data));

    }

}

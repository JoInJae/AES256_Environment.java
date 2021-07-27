package app.controller;

import app.controller.basement.Base_Controller;
import app.data.request.UserDTO;
import app.data.response.Message;
import app.service.User_Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/user", method = RequestMethod.POST)
@RestController
public class User_Controller extends Base_Controller<User_Service> {

    protected User_Controller(User_Service user_service) {
        super(user_service);
    }

    @PostMapping("/put")
    public ResponseEntity<Message<Void>> user_put(@RequestBody UserDTO.Input param){

        return ResponseEntity.ok(service.user_put(param));

    }

    @PostMapping("/id/check")
    public ResponseEntity<Message<Void>> user_id_check(@RequestBody UserDTO.ID_Check param){

        return ResponseEntity.ok(service.user_id_check(param));
    }

    @PostMapping("/login/check")
    public ResponseEntity<Message<UserDTO.Login_Check_Result>> user_login_check(@RequestBody UserDTO.Login_Check param){

        return ResponseEntity.ok(service.user_login_check(param));

    }

    @PostMapping("/reissue")
    public ResponseEntity<Message<UserDTO.Reissue_Result>> user_reissue(@RequestAttribute(name = "access") String access){

        return ResponseEntity.ok(Message.ok(UserDTO.Reissue_Result.builder().access(access).build()));

    }

}

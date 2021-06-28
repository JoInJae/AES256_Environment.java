package app.controller;

import app.controller.basement.Controller_Abstract;

import app.data.request.UserDTO;
import app.service.User_Service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping(value = "/user", method = RequestMethod.POST)
@RestController
public class User_Controller extends Controller_Abstract<User_Service> {

    protected User_Controller(User_Service user_service) {
        super(user_service);
    }

    @PostMapping("/put")
    public void user_put(@RequestBody UserDTO.Input param){

        service.user_put(param);

    }

    @PostMapping("/id/check")
    public void user_id_check(@RequestBody UserDTO.ID_Check param){

        service.user_id_check(param);

    }

    @PostMapping("/login/check")
    public ResponseEntity<UserDTO.Login_Check_Result> user_login_check(@RequestBody UserDTO.Login_Check param){

        return ResponseEntity.ok(service.user_login_check(param));

    }

    @PostMapping("/reissue")
    public ResponseEntity<UserDTO.Reissue_Result> user_reissue(@RequestAttribute(name = "access") String access){

        return ResponseEntity.ok(UserDTO.Reissue_Result.builder().access(access).build());

    }

}

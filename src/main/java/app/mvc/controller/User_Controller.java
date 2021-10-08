package app.mvc.controller;

import app.data.type.Production;
import app.mvc.controller.basement.Base_Controller;
import app.data.request.UserDTO;
import app.data.response.Message;
import app.data.response.MessageB;
import app.mvc.service.User_Service;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(value = "/user", method = RequestMethod.POST)
@RestController
public class User_Controller extends Base_Controller<User_Service> {

    protected User_Controller(User_Service user_service) {
        super(user_service);
    }

    @PostMapping("/put")
    public ResponseEntity<Message> user_put(@Valid @RequestBody UserDTO.Input param, BindingResult bindingResult){

        parameter_check(bindingResult.hasErrors());

        return ResponseEntity.ok(service.user_put(param));

    }

    @PostMapping("/modify")
    public ResponseEntity<Message> user_modify(@RequestBody UserDTO.Update param, @RequestAttribute("uuid")String uuid){

        return ResponseEntity.ok(service.user_modify(param, uuid));

    }

    @PostMapping("/password/modify")
    public ResponseEntity<Message> user_password_modify(@RequestBody UserDTO.Password param, @RequestAttribute("uuid")String uuid){

        return ResponseEntity.ok(service.user_password_modify(param, uuid));

    }

    @PostMapping("/get/all")
    public ResponseEntity<Message> user_get_all(@RequestParam(name = "production", required = false) Production production){

        return ResponseEntity.ok(service.user_get_all(production));

    }

    @PostMapping("/get/info")
    public ResponseEntity<Message> user_get(@RequestAttribute("uuid")String uuid){

        return ResponseEntity.ok(service.user_get(uuid));

    }

    @PostMapping("/id/check")
    public ResponseEntity<Message> user_id_check(@RequestBody UserDTO.ID_Check param){

        return ResponseEntity.ok(service.user_id_check(param));
    }

    @PostMapping("/password/check")
    public ResponseEntity<Message> user_password_check(@RequestBody UserDTO.Password param, @RequestAttribute("uuid")String uuid){

        return ResponseEntity.ok(service.user_password_check(param, uuid));
    }

    @PostMapping("/login/check")
    public ResponseEntity<Message> user_login_check(@RequestBody UserDTO.Login_Check param){

        return ResponseEntity.ok(service.user_login_check(param));

    }

    @PostMapping("/reissue")
    public ResponseEntity<Message> user_reissue(@RequestAttribute(name = "access") String access){

        return ResponseEntity.ok(MessageB.ok(UserDTO.Reissue_Result.builder().access(access).build()));

    }

}

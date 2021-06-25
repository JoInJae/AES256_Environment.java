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

    @PostMapping("/check")
    public ResponseEntity user_check(@RequestBody UserDTO.Input param){


        return ResponseEntity.ok("abc");

    }




}

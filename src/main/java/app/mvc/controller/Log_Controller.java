package app.mvc.controller;

import app.mvc.controller.basement.Base_Controller;
import app.data.request.LogDTO;
import app.data.response.Message;
import app.mvc.service.Log_Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/log", method = RequestMethod.POST)
@RestController
public class Log_Controller extends Base_Controller<Log_Service> {

    protected Log_Controller(Log_Service log_service) {
        super(log_service);
    }

    @PostMapping("/1/put")
    public ResponseEntity<Message> log_v1_put(@RequestBody LogDTO.V1 param, @RequestAttribute("uuid")String uuid){

        return ResponseEntity.ok(service.log_put(param, uuid));

    }

    @PostMapping("/2/put")
    public ResponseEntity<Message> log_v2_put(@RequestBody LogDTO.V2 param, @RequestAttribute("uuid")String uuid){

        return ResponseEntity.ok(service.log_put(param, uuid));

    }

    @PostMapping("/3/put")
    public ResponseEntity<Message> log_v3_put(@RequestBody LogDTO.V3 param, @RequestAttribute("uuid")String uuid){

        return ResponseEntity.ok(service.log_put(param, uuid));

    }

}

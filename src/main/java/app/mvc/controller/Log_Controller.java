package app.mvc.controller;

import app.mvc.controller.basement.Base_Controller;
import app.data.request.LogDTO;
import app.data.response.Message;
import app.mvc.service.Log_Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class Log_Controller extends Base_Controller<Log_Service> {

    protected Log_Controller(Log_Service log_service) {
        super(log_service);
    }

    @PostMapping("/log/1/put")
    public ResponseEntity<Message> log_v1_put(@RequestBody LogDTO.V1 param, @RequestAttribute("uuid")String uuid){

        return ResponseEntity.ok(service.log_put(param, uuid));

    }

    @PostMapping("/log/2/put")
    public ResponseEntity<Message> log_v2_put(@RequestBody LogDTO.V2 param, @RequestAttribute("uuid")String uuid){

        return ResponseEntity.ok(service.log_put(param, uuid));

    }

    @PostMapping("/log/3/put")
    public ResponseEntity<Message> log_v3_put(@RequestBody LogDTO.V3 param, @RequestAttribute("uuid")String uuid){

        return ResponseEntity.ok(service.log_put(param, uuid));

    }

    @PostMapping("/log/put")
    public ResponseEntity<Message> log_put(@RequestBody LogDTO.Basic param, @RequestAttribute("uuid")String uuid){

        return ResponseEntity.ok(service.log_put(param, uuid));

    }

    //마이페이지 정보
    @PostMapping("/stat/analysis/get")
    public ResponseEntity<Message> stat_get(@RequestAttribute("uuid") String uuid ){

        return ResponseEntity.ok(service.stat_get(uuid));

    }


}

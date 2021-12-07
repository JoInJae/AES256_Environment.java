package app.mvc.controller;

import app.config.exception.basement.BaseException;
import app.data.response.type.Response;
import app.mvc.controller.basement.Base_Controller;
import app.data.request.LogDTO;
import app.data.response.Message;
import app.mvc.service.Log_Service;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @PostMapping("/log/get/last/data")
    public ResponseEntity<Message> log_get_last_data(@Valid @RequestBody LogDTO.LastData param, BindingResult bindingResult, @RequestAttribute("uuid")String uuid){

        if(bindingResult.hasErrors()){
            throw new BaseException(Response.FAIL_PARAMETER);
        }

        return ResponseEntity.ok(service.log_get_last_data(param, uuid));

    }

    @PostMapping("/log/put")
    public ResponseEntity<Message> log_put(@RequestBody LogDTO.Basic param, @RequestAttribute("uuid")String uuid){

        return ResponseEntity.ok(service.log_put(param, uuid));

    }

    @PostMapping("/log/get/list")
    public ResponseEntity<Message> log_list_get(@RequestAttribute("uuid")String uuid){

        return ResponseEntity.ok(service.log_list_get(uuid));

    }

    @PostMapping("/game/get/info")
    public ResponseEntity<Message> game_info_get(@RequestAttribute("uuid")String uuid){

        return ResponseEntity.ok(service.game_info_get(uuid));

    }

    //마이페이지 정보
    @PostMapping("/stat/analysis/get")
    public ResponseEntity<Message> stat_get(@RequestAttribute("uuid") String uuid ){

        return ResponseEntity.ok(service.stat_get(uuid));

    }


}

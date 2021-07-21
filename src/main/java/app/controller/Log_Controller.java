package app.controller;

import app.controller.basement.Base_Controller;
import app.data.request.LogDTO;
import app.service.Log_Service;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/log", method = RequestMethod.POST)
@RestController
public class Log_Controller extends Base_Controller<Log_Service> {

    protected Log_Controller(Log_Service log_service) {
        super(log_service);
    }

    @PostMapping("/put")
    public void log_put(@RequestBody LogDTO.Input param, @RequestAttribute("uuid")String uuid){

        service.log_put(param, uuid);

    }


}

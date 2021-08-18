package app.mvc.service;

import app.data.request.LogDTO;
import app.data.response.Message;

public interface Log_Service {

    Message log_put(LogDTO.Input param, String uuid);

}

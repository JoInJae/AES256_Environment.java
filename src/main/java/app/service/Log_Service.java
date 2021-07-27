package app.service;

import app.data.request.LogDTO;
import app.data.response.Message;

public interface Log_Service {

    Message<Void> log_put(LogDTO.Input param, String uuid);

}

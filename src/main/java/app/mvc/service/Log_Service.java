package app.mvc.service;

import app.data.request.LogDTO;
import app.data.response.Message;
import app.data.response.MessageB;

public interface Log_Service {

    Message log_put(LogDTO.V1 param, String uuid);
    Message log_put(LogDTO.V2 param, String uuid);
    Message log_put(LogDTO.V3 param, String uuid);
    Message log_put(LogDTO.Basic param, String uuid);

    MessageB stat_get(String uuid);

}

package app.service;

import app.data.request.LogDTO;

public interface Log_Service {

    void log_put(LogDTO.Input param, String uuid);

}

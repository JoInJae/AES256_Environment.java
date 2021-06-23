package app.controller._base;

import org.springframework.web.bind.annotation.RequestBody;

public interface Controller_Interface <Param, Result>{

    void insert(@RequestBody Param param);
    void update(@RequestBody Param param);
    void remove(@RequestBody Param param);

}

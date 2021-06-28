package app.service;

import app.data.request.UserDTO;

public interface User_Service {

    void user_put(UserDTO.Input param);

    void user_id_check(UserDTO.ID_Check param);

    UserDTO.Login_Check_Result user_login_check(UserDTO.Login_Check param);

}

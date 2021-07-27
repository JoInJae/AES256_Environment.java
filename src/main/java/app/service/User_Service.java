package app.service;

import app.data.request.UserDTO;
import app.data.response.Message;

public interface User_Service {

    Message<Void> user_put(UserDTO.Input param);

    Message<Void> user_id_check(UserDTO.ID_Check param);

    Message<UserDTO.Login_Check_Result> user_login_check(UserDTO.Login_Check param);

}

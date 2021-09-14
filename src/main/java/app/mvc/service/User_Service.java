package app.mvc.service;

import app.data.request.UserDTO;
import app.data.response.Message;
import app.data.response.MessageB;
import app.data.type.Production;

import java.util.List;

public interface User_Service {

    Message user_put(UserDTO.Input param);

    Message user_modify(UserDTO.Update param, String uuid);

    Message user_password_modify(UserDTO.Password param, String uuid);

    Message user_id_check(UserDTO.ID_Check param);

    Message user_password_check(UserDTO.Password param, String uuid);

    MessageB<UserDTO.Login_Check_Result> user_login_check(UserDTO.Login_Check param);

    MessageB<List<UserDTO.Simple_Info_Result>> user_get_all(Production production);

    MessageB<UserDTO.Info_Result> user_get(String uuid);

}

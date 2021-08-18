package app.mvc.service;

import app.data.request.UserDTO;
import app.data.response.Message;
import app.data.response.MessageB;
import app.data.type.Production;

import java.util.List;

public interface User_Service {

    Message user_put(UserDTO.Input param);

    Message user_id_check(UserDTO.ID_Check param);

    MessageB<UserDTO.Login_Check_Result> user_login_check(UserDTO.Login_Check param);

    MessageB<List<UserDTO.Simple_Info_Result>> user_get_all(Production production);
}

package app.mvc.service;

import app.data.request.AdminDTO;
import app.data.response.Message;
import app.data.response.MessageB;
import app.data.type.Production;

import javax.servlet.http.HttpServletResponse;

public interface Admin_Service {

    Message admin_put(AdminDTO.Input param);

    Message admin_login(AdminDTO.Login_Check param, HttpServletResponse response);

    Message admin_reissue(String value);

    MessageB main_info_get(Production production);
}

package app.mvc.controller.basement;

import app.data.response.type.Response;
import app.exception.basement.BaseException;

public abstract class Base_Controller<Service>{

    protected final Service service;

    protected Base_Controller(Service service) {
        this.service = service;
    }

    protected  void parameter_check(boolean check){

        if(check){
            throw new BaseException(Response.FAIL_PARAMETER);
        }

    }

}




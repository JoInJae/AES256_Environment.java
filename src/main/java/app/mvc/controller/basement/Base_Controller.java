package app.mvc.controller.basement;


public abstract class Base_Controller<Service>{

    protected final Service service;

    protected Base_Controller(Service service) {
        this.service = service;
    }

}

package app.controller._base;


public abstract class Controller_Abstract<Service>{

    protected final Service service;

    protected Controller_Abstract(Service service) {
        this.service = service;
    }

}

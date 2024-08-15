package tr.gov.gib.sposodeme.exception;

import tr.gov.gib.gibcore.util.ServiceMessage;

public class GibException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private ServiceMessage serviceMessage;

    public GibException(String message) {
        super(message);
    }

    public GibException(ServiceMessage serviceMessage,String message) {
        super(message);
        this.serviceMessage = serviceMessage;
    }

    public ServiceMessage getServiceMessage() {
        return serviceMessage;
    }

    public void setServiceMessage(ServiceMessage serviceMessage) {
        this.serviceMessage = serviceMessage;
    }
}

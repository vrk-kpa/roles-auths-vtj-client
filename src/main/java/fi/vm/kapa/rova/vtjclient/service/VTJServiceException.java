package fi.vm.kapa.rova.vtjclient.service;

public class VTJServiceException extends Exception {
    private static final long serialVersionUID = 1L;

    public VTJServiceException(String msg, Throwable t) {
        super(msg, t);
    }
}

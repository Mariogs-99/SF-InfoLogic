package com.infologic.catalogos_service.exceptions;

public class ErrorResponse {
    private final String mensaje;

    public ErrorResponse(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }
}

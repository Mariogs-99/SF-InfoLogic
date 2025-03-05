package com.infologic.cliente_service.exceptions;

public class ErrorResponse {
    private final String mensaje;

    public ErrorResponse(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }
}

package com.valentinlopez.ventas.prueba.util;

import java.util.Map;

public class ValidationException extends IllegalArgumentException {

    private final Map<String, String> errors;

    public ValidationException(Map<String, String> errors) {
        super("Errores de validación");
        this.errors = errors;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}

package br.com.avantews.resource.exception;

import java.io.Serializable;

public class FieldMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    private String fieldName;
    private String message;

    public FieldMessage() {
    }

    public FieldMessage(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getFieldName() {
        return fieldName;
    }

    public FieldMessage comFieldName(String fieldName) {
        this.fieldName = fieldName;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public FieldMessage comMessage(String message) {
        this.message = message;
        return this;
    }
}

package com.miro.framework.enums;

public enum SignupPageValidationErrors implements IEnumValue {

    EMAIL_ALREADY_REGISTERED("emailError", "Sorry, this email is already registered"),
    PASSWORD_TOO_SHORT("signup-form-password", "Please use 8+ characters for secure password."),
    NAME_IS_EMPTY("nameError", "Please enter your name."),
    INVALID_EMAIL_DOMAIN("emailError", "This email can not be registered, please try another domain");


    private String key;
    private String value;

    SignupPageValidationErrors(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    @Override
    public String getValue() {
        return value;
    }

}

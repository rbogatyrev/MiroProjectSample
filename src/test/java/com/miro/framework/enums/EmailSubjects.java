package com.miro.framework.enums;

public enum EmailSubjects implements IEnumValue {
    EMAIL_CONFIRMATION("Here's Your Confirmation Code");

    private String key;

    EmailSubjects(String key) {
        this.key = key;
    }

    @Override
    public String getValue() {
        return key;
    }
}

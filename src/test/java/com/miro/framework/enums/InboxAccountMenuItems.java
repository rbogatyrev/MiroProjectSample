package com.miro.framework.enums;

public enum InboxAccountMenuItems implements IEnumValue {
    CREATE_ACCOUNT("Создать аккаунт"),
    CREATE("Создать");

    private String key;

    InboxAccountMenuItems(String key) {
        this.key = key;
    }

    @Override
    public String getValue() {
        return key;
    }
}

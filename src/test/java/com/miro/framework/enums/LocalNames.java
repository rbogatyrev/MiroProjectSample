package com.miro.framework.enums;

public enum LocalNames implements IEnumValue {
    INBOX_PAGE_REFRESH_BUTTON("Обновить");

    private String key;

    LocalNames(String key) {
        this.key = key;
    }

    @Override
    public String getValue() {
        return key;
    }
}

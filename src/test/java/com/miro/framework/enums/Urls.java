package com.miro.framework.enums;

import com.miro.framework.configuration.EnvironmentConfig;
import org.aeonbits.owner.ConfigFactory;

public enum Urls implements IEnumValue {
    MAIN_PAGE(""),
    SIGNUP_PAGE("signup"),
    EMAIL_CONFIRM("email-confirm"),
    WELCOME_PAGE("app"),
    BOARD_PAGE("app/board"),
    INBOX_PAGE_RU("ru");

    private static EnvironmentConfig env = ConfigFactory.create(EnvironmentConfig.class, System.getProperties());
    private String key;

    Urls(String key) {
        this.key = key;
    }

    @Override
    public String getValue() {
        if (this.key.equals(INBOX_PAGE_RU.key)) {
            return env.tempMailUrl() + key;
        } else {
            return env.productionUrl() + key;
        }
    }
}

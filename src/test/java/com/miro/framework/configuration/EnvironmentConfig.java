package com.miro.framework.configuration;

import org.aeonbits.owner.Config;

/**
 * Configuration class that contains environment variables
 */
public interface EnvironmentConfig extends Config {

    @DefaultValue("https://miro.com/")
    @Key("prod")
    String productionUrl();

    @DefaultValue("https://mail.tm/")
    @Key("tempmail.url")
    String tempMailUrl();

}

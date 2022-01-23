package com.miro.framework.configuration;

import org.aeonbits.owner.Config;

/**
 * Configuration class that contains templates for user variables
 */
public interface UserConfig extends Config {

    @DefaultValue("miro_%s")
    @Key("email.template")
    String emailTemplate();

}

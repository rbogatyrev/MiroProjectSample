package com.miro.framework.configuration;

import org.aeonbits.owner.Config;

/**
 * Configuration class that contains webdriver setup properties
 */
public interface WebDriverConfig extends Config {

    @DefaultValue("false")
    @Key("driver.remote")
    boolean isRemote();

    @DefaultValue("http://localhost:4444/wd/hub")
    @Key("remote.url")
    String remoteUrl();
    
    @DefaultValue("91.0")
    @Key("remote.chrome.version")
    String chromeVersion();

}

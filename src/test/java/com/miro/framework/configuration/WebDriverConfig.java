package com.miro.framework.configuration;

import org.aeonbits.owner.Config;

/**
 * Configuration class that contains webdriver setup properties
 */
public interface WebDriverConfig extends Config {

    @DefaultValue("false")
    @Key("driver.remote")
    boolean isRemote();

    @DefaultValue("https://user1:1234@selenoid.autotests.cloud/wd/hub")
    @Key("remote.url")
    String remoteUrl();

    @DefaultValue("https://selenoid.autotests.cloud/video/")
    @Key("video.urlpart")
    String videoUrlPart();


    @DefaultValue("91.0")
    @Key("remote.chrome.version")
    String chromeVersion();

}

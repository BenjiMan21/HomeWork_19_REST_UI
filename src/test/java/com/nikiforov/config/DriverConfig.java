package com.nikiforov.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:config/${environment}.properties"
})
public interface DriverConfig extends Config {

    @Key("browser")
    @DefaultValue("chrome")
    String browserName();

    @Key("version")
    @DefaultValue("100.0")
    String browserVersion();

    @Key("browserResolution")
    @DefaultValue("1920x1080")
    String browserSize();

    @Key("remoteUrl")
    String browserRemoteUrl();

}

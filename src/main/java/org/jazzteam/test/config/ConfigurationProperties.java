package org.jazzteam.test.config;

import org.jazzteam.test.core.BrowserType;
import ru.qatools.properties.Property;
import ru.qatools.properties.PropertyLoader;
import ru.qatools.properties.Resource;

/**
 * Created by Savenok.Ivan on 10.04.2017.
 */

@Resource.Classpath("configuration.properties")
public class ConfigurationProperties {

    @Property("endpoint")
    public String endpoint;

    @Property("timeout.implicitlywait")
    private Long timeoutImplicitlyWait = 5L;

    @Property("timeout.pageload")
    private Long timeoutPageLoad = 30L;

    @Property("test.browser.type")
    private BrowserType browserType = BrowserType.CHROME;

    @Property("test.chrome.driver.path")
    private String chromeDriverPath;

    @Property("test.default.hub")
    private String defaultHub;

    @Property("test.locale")
    private String locale = "en";

    private static ConfigurationProperties configProperties;

    private ConfigurationProperties(){
        PropertyLoader.newInstance().populate(this);
    }

    public static ConfigurationProperties getInstance(){
        if (configProperties == null){
            configProperties = new ConfigurationProperties();
        }
        return configProperties;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public Long getTimeoutImplicitlyWait() {
        return timeoutImplicitlyWait;
    }

    public Long getTimeoutPageLoad() {
        return timeoutPageLoad;
    }

    public BrowserType getBrowserType() {
        return browserType;
    }

    public String getChromeDriverPath() {
        return chromeDriverPath;
    }

    public String getDefaultHub() {
        return defaultHub;
    }

    public String getLocale() {
        return locale;
    }
}

package org.jazzteam.test.core;

import org.jazzteam.test.config.ConfigurationProperties;
import org.jazzteam.test.config.ApplicationContextUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

/**
 * Created by Savenok.Ivan on 04.04.2017.
 * <p/>
 * Class contains only driver initialization and stop methods.
 * <p/>
 * Driver methods, working with controls is in @DriverExtension.class
 */
@Component
public class DriverManager {
    private static final Logger logger = LoggerFactory.getLogger(DriverManager.class);
    private ThreadLocal<DriverExtension> driverExtension = new ThreadLocal<>();

    /**
     * init browser with all params, according to specified type
     */
    @PostConstruct
    private void init() {
        ConfigurationProperties configProperties = ConfigurationProperties.getInstance();
        String chromeDriverPath = configProperties.getChromeDriverPath();
        if (configProperties.getBrowserType().equals(BrowserType.CHROME) &&
                (chromeDriverPath == null || chromeDriverPath.isEmpty())) {
            throw new IllegalArgumentException("Chrome browser usage expected but path to ChromeDriver not specified");
        }
        System.setProperty("webdriver.chrome.driver", buildPathToDriver(chromeDriverPath));
    }

    /**
     * Get Selenium elements
     *
     * @return Selenium elements
     */
    public DriverExtension getDriverExtension() {
        if (driverExtension.get() == null)
            driverExtension.set(ApplicationContextUtils.getBean(DriverExtension.class));
        return driverExtension.get();
    }

    /**
     * Stop Selenium if started
     */
    public void stop() {
        logger.info("IN " + Thread.currentThread().getName() + " Selenium Stop");
        getDriverExtension().stop();
        driverExtension.remove();
    }

    public void handleBrowserInstance() {
        logger.info("IN " + Thread.currentThread().getName() + " Browser Clear Cookies");
        getDriverExtension().clearAllCookies();
    }

    public ConfigurationProperties getConfigProperties() {
        return ConfigurationProperties.getInstance();
    }

    private String buildPathToDriver(String driverRelativePath) {
        try {
            return FilenameUtils.separatorsToSystem(new File("./").getCanonicalPath() + File.separator + driverRelativePath);
        } catch (IOException ioException) {
            throw new RuntimeException("Could not parse absolute path to WebDriver. Relative path is " + driverRelativePath);
        }
    }
}

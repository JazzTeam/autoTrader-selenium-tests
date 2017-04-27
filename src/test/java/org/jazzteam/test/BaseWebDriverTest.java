package org.jazzteam.test;

import org.jazzteam.test.config.WorkBeanConfiguration;
import org.jazzteam.test.core.DriverManager;

import org.jazzteam.test.steps.header.HeaderSteps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * Created by Savenok.Ivan on 10.04.2017.
 */

@ContextConfiguration(classes = {WorkBeanConfiguration.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
public abstract class BaseWebDriverTest extends AbstractTestNGSpringContextTests {

    protected final static boolean AVAILABLE = true;
    protected final static boolean UNAVAILABLE = false;
    protected static String startUrl;

    @Autowired
    protected DriverManager driverManager;

    @Autowired
    protected HeaderSteps headerSteps;


    public DriverManager getDriverManager() {
        return driverManager;
    }

    @BeforeMethod(alwaysRun = true)
    public void setUpBeforeEveryMethod() {
        startUrl = getDriverManager().getConfigProperties().getEndpoint();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownAfterEveryMethod() {
        String defaultHub = getDriverManager().getConfigProperties().getDefaultHub();
        if (defaultHub == null || defaultHub.isEmpty()) {
            getDriverManager().handleBrowserInstance();
        } else {
            getDriverManager().stop();
        }
    }

}

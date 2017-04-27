package org.jazzteam.test.core;

import org.jazzteam.test.config.ConfigurationProperties;
import org.jazzteam.test.utils.CommonDriverUtils;
import org.jazzteam.test.utils.ScreenshotUtils;
import org.hamcrest.Matcher;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.internal.WrapsDriver;
import org.openqa.selenium.support.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.stqa.selenium.factory.WebDriverFactory;
import ru.stqa.selenium.factory.WebDriverFactoryMode;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.MatcherDecorators.should;
import static ru.yandex.qatools.htmlelements.matchers.MatcherDecorators.timeoutHasExpired;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.*;
import static ru.yandex.qatools.matchers.webdriver.EnabledMatcher.enabled;

/**
 * Created by Savenok.Ivan on 04.04.2017.
 * <p>
 * DriverExtension.class contains methods, that wrap corresponding driver methods
 */

@Component
public class DriverExtension implements WrapsDriver {
    private static final Logger logger = LoggerFactory.getLogger(DriverExtension.class);
    private static final long POOLING_INTERVAL_MILLISECONDS = 500;
    public static final String FACTORY_INTERNAL = "factoryInternal";
    public static final String DEFAULT_HUB = "defaultHub";
    public static final String VALUE = "value";
    private ConfigurationProperties configProperties;

    @PostConstruct
    protected void init() {
        configProperties = ConfigurationProperties.getInstance();
        if ((configProperties.getDefaultHub() != null) && (!configProperties.getDefaultHub().isEmpty())) {
            WebDriverFactory.setMode(WebDriverFactoryMode.THREADLOCAL_SINGLETON);
            WebDriverFactory.setDefaultHub(configProperties.getDefaultHub());
            logger.info("#############    THREADLOCAL_SINGLTONE MODE  is ON! ####################");
            logger.info("#############    GRID HUB IS ON    ####################");
        } else {
            WebDriverFactory.setMode(WebDriverFactoryMode.SINGLETON);
        }

        Class<WebDriverFactory> clazz = WebDriverFactory.class;
        Field currField = null;
        try {
            currField = clazz.getDeclaredField(FACTORY_INTERNAL);
            currField.setAccessible(true);
        } catch (NoSuchFieldException e) {
            logger.error(e.getMessage());
        }

        try {
            Field defaultHubVar = currField.getType().getDeclaredField(DEFAULT_HUB);
            defaultHubVar.setAccessible(true);
            String defaultHubStr = (String) defaultHubVar.get(currField.get(null));
            logger.info("hub is set to: " + defaultHubStr);

            logger.info(currField.get(null).getClass().getName() + " driver storage is used...");

        } catch (IllegalAccessException | NoSuchFieldException e) {
            logger.error(e.getMessage());

        }
    }

    @Override
    public WebDriver getWrappedDriver() {
        return BrowserTypeFactory.createBrowser(configProperties.getBrowserType());
    }

    protected void stop() {
        BrowserTypeFactory.closeBrowser(getWrappedDriver());
    }

    public void clearAllCookies() {
        getWrappedDriver().manage().deleteAllCookies();
    }


    public void open(String url) {
        getWrappedDriver().get(url);
    }


    /**
     * use for delay if needed
     *
     * @param waitPeriod in ms
     */

    public void waiting(long waitPeriod) {
        try {
            TimeUnit.MILLISECONDS.sleep(waitPeriod);
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * Click to element with check this enabled and move focus to this
     *
     * @param element
     */

    public void safeClickElement(WebElement element) {
        try {
            if (element != null) {
                logger.info(
                        "IN " + Thread.currentThread().getName() + " safe click element " + element.toString() + " / " + this.toString() +
                                " / " + getWrappedDriver().toString());
                checkUntil(element, enabled());
                jsScrollIntoView(element);
                element.click();
            }
        } catch (AssertionError e) {
            ScreenshotUtils.makeElementScreenShotWithAllure(getWrappedDriver(), element);
            logger.info(" element isn't click  at the page ");
            logger.error(e.getMessage());
            throw e;
        } catch (NullPointerException error){
            ScreenshotUtils.makeScreenShotWithAllure(getWrappedDriver());
            logger.info("Sorry, page or element is not exists");
            logger.error(error.getMessage());
            logger.error(error.getMessage());
            throw new Error("Sorry, element on page not exists");
        }
    }


    /**
     * Use if element contains inside other element in center
     * for click to parent element because safeClickElement() clickable in center
     *
     * @param element
     */
    public void safeClickElementTopLeft(WebElement element) {
        logger.info(
                "IN " + Thread.currentThread().getName() + " safeClickElementTopLeft " + element.toString() + "  /  " + this.toString());
        try {
            checkUntil(element, exists());
            new Actions(getWrappedDriver()).moveToElement(element, 1, 1).click().build().perform();

        } catch (NoSuchElementException e) {
            ScreenshotUtils.makeElementScreenShotWithAllure(getWrappedDriver(), element);
            logger.info(" element isn't present at the page");
            logger.error(e.getMessage());
            throw e;
        }
    }

    /**
     * Use if element contains inside other element in center
     * for click to parent element because safeClickElement() clickable in center
     *
     * @param element
     */
    public void safeClickElementTopRight(WebElement element) {
        logger.info(
                "IN " + Thread.currentThread().getName() + " safeClickElementTopRight " + element.toString() + "  /  " + this.toString());
        try {
            checkUntil(element, exists());
            new Actions(getWrappedDriver()).moveToElement(element, element.getSize().getWidth() - 1, 1).click().build().perform();

        } catch (AssertionError e) {
            ScreenshotUtils.makeElementScreenShotWithAllure(getWrappedDriver(), element);
            logger.info(" element isn't present at the page");
            logger.error(e.getMessage());
            throw e;
        }
    }


    /**
     * Scrolled element if it invisible to visible zone
     *
     * @param element
     */
    public void jsScrollIntoView(WebElement element) {
        try {
            CommonDriverUtils.waitForJQueryProcessing(getWrappedDriver(), configProperties.getTimeoutPageLoad());
            JavascriptExecutor executor = (JavascriptExecutor) getWrappedDriver();
            executor.executeScript("arguments[0].scrollIntoView(true);", element);
            waiting(500);
        } catch (NoSuchElementException e) {
            ScreenshotUtils.makeElementScreenShotWithAllure(getWrappedDriver(), element);
            logger.error(e.getMessage());
            throw e;
        }
    }

    /**
     * Enter text in to editable area with check wait for result. Use explicite wait
     *
     * @param element
     * @param keys
     */
    public void safeEnterText(WebElement element, String keys) {
        safeEnterText(element, keys, false);
    }

    /**
     * Enter text in to editable area with check wait for result and send return key. Use explicite wait
     *
     * @param element
     * @param keys
     * @param withReturnKey
     */
    public void safeEnterText(WebElement element, String keys, boolean withReturnKey) {
        try {
            logger.info("IN " + Thread.currentThread().getName() + " safeEnterText " + element.toString() + " in element " + keys + "  /  " +
                    this.toString());
            checkUntil(element, enabled());
            int tryInput = 5; //count repeat input text
            while (!element.getAttribute(VALUE).equalsIgnoreCase(keys) && tryInput > 0) {
                ///  element.click();
                jsScrollIntoView(element);
                safeClickElementTopLeft(element);
                element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
                /// magic waiting don't delete
                waiting(220);
                element.sendKeys(keys.isEmpty() ? Keys.DELETE : keys);
                tryInput--;
            }
            checkUntil(element, anyOf(hasText(keys), hasValue(keys)));
            if (withReturnKey)
                element.sendKeys(Keys.chord(Keys.RETURN));
        } catch (AssertionError error) {
            ScreenshotUtils.makeElementScreenShotWithAllure(getWrappedDriver(), element);
            logger.info("text not verify or element not exists");
            logger.error(error.getMessage());
            throw error;
        } catch (NullPointerException error){
            ScreenshotUtils.makeScreenShotWithAllure(getWrappedDriver());
            logger.info("Sorry, page or element is not exists");
            logger.error(error.getMessage());
            throw new Error("Sorry, element or page is not exists");
        }
    }

    /**
     * Return element if exists or null if not exists
     *
     * @param element
     * @param <T>
     * @return
     */

    public <T extends WebElement> T isExistsReturnWebElement(T element) {
        T result;
        getWrappedDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        Wait<WebDriver> wait = new FluentWait<>(getWrappedDriver()).withTimeout(1, TimeUnit.MILLISECONDS);
        Instant startTime = Instant.now();
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            result = element;
        } catch (TimeoutException | StaleElementReferenceException | NoSuchElementException e) {
            Instant endTime = Instant.now();
            Duration duration = Duration.between(startTime, endTime);
            logger.info(String.format("Element doesn't exist, time = %s ms", duration.toMillis()));
            result = null;
        }
        getWrappedDriver().manage().timeouts().implicitlyWait(configProperties.getTimeoutImplicitlyWait(), TimeUnit.SECONDS);

        return result;
    }

    /**
     * wait until actual equals to matcher
     *
     * @param actual
     * @param matcher
     * @param timeMilliseconds max interval wait
     * @param <T>
     */

    public <T> void checkUntil(T actual, final Matcher<? super T> matcher, long timeMilliseconds) {

        assertThat(actual,
                should(matcher).whileWaitingUntil(timeoutHasExpired(timeMilliseconds).withPollingInterval(POOLING_INTERVAL_MILLISECONDS)));
    }

    public <T> void checkUntil(T actual, final Matcher<? super T> matcher) {
        checkUntil(actual, matcher, TimeUnit.SECONDS.toMillis(configProperties.getTimeoutPageLoad()));
    }
}

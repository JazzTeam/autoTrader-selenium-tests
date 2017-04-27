package org.jazzteam.test.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by Savenok.Ivan on 10.04.2017.
 */

public final class CommonDriverUtils {
    private static final Logger logger = LoggerFactory.getLogger(CommonDriverUtils.class);


    public static boolean waitForJQueryProcessing(WebDriver driver, long timeOutInSeconds) {
        boolean jQcondition = false;
        try {
            new WebDriverWait(driver, timeOutInSeconds) {
            }.until(new ExpectedCondition<Boolean>() {

                @Override
                public Boolean apply(WebDriver driverObject) {
                    return (Boolean) ((JavascriptExecutor) driverObject)
                            .executeScript("return jQuery.active == 0");
                }
            });
            jQcondition = (Boolean) ((JavascriptExecutor) driver)
                    .executeScript("return window.jQuery != undefined && jQuery.active === 0");
            return jQcondition;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return jQcondition;
    }


}

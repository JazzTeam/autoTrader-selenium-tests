package org.jazzteam.test.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.cropper.indent.BlurFilter;
import ru.yandex.qatools.ashot.cropper.indent.IndentCropper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;


/**
 * Created by Savenok.Ivan on 10.04.2017.
 */

public final class ScreenshotUtils {
    private static final Logger logger = LoggerFactory.getLogger(ScreenshotUtils.class);

    private static final String DEFAULT_FORMAT = "png";
    private static final String DEFAULT_MESSAGE = "Screen for text";

    private static byte[] saveScreenshot(String message, byte[] screenShot) {
        return screenShot;
    }


    private static Screenshot makeScreenShotOfCurrentPage(WebDriver webDriver) {
        return new AShot().takeScreenshot(webDriver);
    }

    private static Screenshot makeScreenShotOfFailedPageArea(WebDriver webDriver, WebElement faultElement) {
        return new AShot()
                .imageCropper(new IndentCropper()
                        .addIndentFilter(new BlurFilter()))
                .takeScreenshot(webDriver, faultElement);
    }

    public static void makeScreenShotWithAllure(WebDriver webDriver) {
        Screenshot currentShot = makeScreenShotOfCurrentPage(webDriver);

        saveScreenshot(DEFAULT_MESSAGE, convertImageToByteArray(currentShot.getImage()));
    }

    public static void makeElementScreenShotWithAllure(WebDriver webDriver, WebElement faultElement) {
        Screenshot currentShot = makeScreenShotOfFailedPageArea(webDriver, faultElement);
        saveScreenshot(DEFAULT_MESSAGE, convertImageToByteArray(currentShot.getImage()));
    }


    private static byte[] convertImageToByteArray(BufferedImage image) {
        ByteArrayOutputStream byteArrayStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, DEFAULT_FORMAT, byteArrayStream);
            logger.info("Successfully wrote screenshot to byte array output stream");
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("ERROR: Cannot write screenshot to ByteArrayOutputStream");
        } finally {
            try {
                byteArrayStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                byteArrayStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        byte[] imageBytes = byteArrayStream.toByteArray();

        if (imageBytes.length == 0) {
            String errorMessage = "ERROR: Converted byte array for screenshot is empty.";
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
        logger.info("Converted image screenshot to byte array. Byte array size is: "
                + imageBytes.length);

        return imageBytes;
    }

}

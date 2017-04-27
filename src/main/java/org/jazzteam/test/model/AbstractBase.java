package org.jazzteam.test.model;

import org.jazzteam.test.config.ApplicationContextUtils;
import org.jazzteam.test.core.DriverExtension;
import org.jazzteam.test.core.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;

/**
 * Created by Savenok.Ivan on 10.04.2017.
 */

@Component
public abstract class  AbstractBase extends HtmlElement {

    public static final int WAIT_PERIOD = 1000;

    /**
     * This selector is specially converted to absolute paths,
     * because the web element is outside the extended component of forms and relative paths it is not visible.
     */
    @FindBy(css = "//*[@id=\"fsrOverlay\"]/div/div/div/a")
    protected WebElement closeFeedbackDialogButton;

    /**
     * Declared as 'static' cause is called inside
     * 'public static <T extends AbstractBase> T getInitPageBean(Class<T> var1, String qualifier)'
     *
     * @return
     */
    public static DriverExtension getDriverExtension() {
        return ApplicationContextUtils.getApplicationContext().getBean(DriverManager.class)
                .getDriverExtension();
    }

    /**
     * Gets element bean and initializes this element with event after initial;
     *
     * @param var1      Class of Page Object (element)
     * @param <T>       page object (element) type
     * @return initialized element
     */

    public static <T extends AbstractBase> T getInitPageBean(Class<T> var1) {
        T result = AbstractBase.getInitPageBeanFast(var1);
        result.afterPageInitialization();
        return result;
    }


    /**
     * Gets element bean and initializes this element without events after initial;
     *
     * @param var1      Class of Page Object (element)
     * @param <T>       page object (element) type
     * @return initialized element
     */

    public static <T extends AbstractBase> T getInitPageBeanFast(Class<T> var1) {
        return HtmlElementLoader.create(var1, getDriverExtension().getWrappedDriver());
    }



    protected void afterPageInitialization() {

    }

    /**
     * This method is intentionally left empty. It's declared here to hide errors in AbstractBase.class childs,
     * cause HtmlElement.class doesn't implement it in version 1.14
     * <p>
     * It brings no harm, cause screenshoting is implemented via yandex ashot library.
     */
    @Override
    public <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException {
        return null;
    }

    public void handleFeedbackDialog(){
        if(getDriverExtension().isExistsReturnWebElement(closeFeedbackDialogButton) != null){
            getDriverExtension().safeClickElement(closeFeedbackDialogButton);
        }
    }
}

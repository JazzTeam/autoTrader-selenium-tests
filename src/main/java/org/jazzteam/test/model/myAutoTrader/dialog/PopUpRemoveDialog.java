package org.jazzteam.test.model.myAutoTrader.dialog;

import org.jazzteam.test.model.AbstractBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

/**
 * Created by Savenok.Ivan on 17.04.2017.
 */

@Component
@FindBy(css = ".modal")
public class PopUpRemoveDialog extends AbstractBase {

    public static final String IFRAME_SELECTOR = "js-confirm-delete-advert";
    public static final String TAG_IFRAME = "iframe";

    public WebDriver iFrame = null;

    public void confirm() {
        iFrame = getDriverExtension().getWrappedDriver().switchTo().frame(findElement(By.tagName(TAG_IFRAME)));
        getDriverExtension().safeClickElementTopLeft(iFrame.findElement(By.id(IFRAME_SELECTOR)));
    }
}

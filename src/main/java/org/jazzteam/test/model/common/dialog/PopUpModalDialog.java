package org.jazzteam.test.model.common.dialog;

import org.jazzteam.test.model.AbstractBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

/**
 * Created by Savenok.Ivan on 08.04.2017.
 */

@Component
@FindBy(id = "_tealiumModalWindow")
public class PopUpModalDialog extends AbstractBase {

    @FindBy(id = "_tealiumModalClose")
    protected WebElement closeDialogButton;

    public WebElement getCloseDialogButton() {
        return getDriverExtension().isExistsReturnWebElement(closeDialogButton);
    }

    public void closeWindow() {
        getDriverExtension().safeClickElement(getCloseDialogButton());
    }
}

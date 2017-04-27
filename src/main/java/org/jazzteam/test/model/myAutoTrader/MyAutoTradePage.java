package org.jazzteam.test.model.myAutoTrader;

import org.jazzteam.test.model.AbstractBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

/**
 * Created by Savenok.Ivan on 17.04.2017.
 */

@Component
@FindBy(className = "my-autotrader-page")
public class MyAutoTradePage extends AbstractBase {

    @FindBy(id = "savedAdverts")
    private WebElement savedAdvertsLink;

    public void doOpenSavedAdverts() {
        if (getDriverExtension().isExistsReturnWebElement(savedAdvertsLink) != null) {
            getDriverExtension().safeClickElementTopLeft(savedAdvertsLink);
        }
    }

}

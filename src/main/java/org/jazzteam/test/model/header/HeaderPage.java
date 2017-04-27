package org.jazzteam.test.model.header;

import org.jazzteam.test.model.AbstractBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

/**
 * Created by Savenok.Ivan on 04.04.2017.
 */

@Component
@FindBy(css = "body")
public class HeaderPage extends AbstractBase {
    @FindBy(css = ".inline-links-list__item")
    private WebElement searchLink;

    @FindBy(css = "#signUp_header_main")
    private WebElement signUpLink;

    @FindBy(css = "#signIn_header_main")
    private WebElement signInLink;
    @FindBy(css = ".mainNav-menu__button--myGarage")
    private WebElement myAutoTradeLink;

    public void doOpenSearch() {
        if (getDriverExtension().isExistsReturnWebElement(searchLink) != null) {
            getDriverExtension().safeClickElement(searchLink);
        }
    }

    public void doOpenLoginPage() {
        if (getDriverExtension().isExistsReturnWebElement(signInLink) != null) {
            getDriverExtension().safeClickElement(signInLink);
        }
    }

    public void doOpenRegisterPage() {
        if (getDriverExtension().isExistsReturnWebElement(signUpLink) != null) {
            getDriverExtension().safeClickElement(signUpLink);
        }
    }

    public void doOpenMyAutoTradePage() {
        if (getDriverExtension().isExistsReturnWebElement(myAutoTradeLink) != null) {
            getDriverExtension().safeClickElementTopLeft(myAutoTradeLink);
        }
    }


}

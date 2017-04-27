package org.jazzteam.test.model.login;

import org.jazzteam.test.model.AbstractBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

/**
 * Created by Savenok.Ivan on 05.04.2017.
 */

@Component
@FindBy(css = ".social__holder")
public abstract class BaseSignInPage extends AbstractBase {
    public abstract WebElement getEmail();
    public abstract WebElement getSubmitButton();
    public abstract WebElement getPassword();

    public void setEmail(String email){
        if (getDriverExtension().isExistsReturnWebElement(getEmail()) != null) {
            getDriverExtension().safeEnterText(getEmail(), email);
        }
    }    public void setPassword(String password){
        if (getDriverExtension().isExistsReturnWebElement(getPassword()) != null) {
            getDriverExtension().safeEnterText(getPassword(), password);
        }
    }

    public void clickSubmit(){
        if (getDriverExtension().isExistsReturnWebElement(getPassword()) != null) {
            getDriverExtension().safeClickElement(getSubmitButton());
        }
    }



}

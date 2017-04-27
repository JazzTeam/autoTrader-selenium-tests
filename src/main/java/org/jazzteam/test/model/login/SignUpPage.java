package org.jazzteam.test.model.login;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Savenok.Ivan on 05.04.2017.
 */
public class SignUpPage extends BaseSignInPage {

    @FindBy(css = "#signin-email")
    private WebElement loginEmailField;

    @FindBy(css = "#signin-password")
    private WebElement loginPassword;


    @FindBy(css = "#signInSubmit")
    private WebElement loginButton;

    @Override
    public WebElement getEmail() {
        return loginEmailField;
    }

    @Override
    public WebElement getSubmitButton() {
        return loginButton;
    }

    @Override
    public WebElement getPassword() {
        return loginPassword;
    }
}

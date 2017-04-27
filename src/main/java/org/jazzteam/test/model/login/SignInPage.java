package org.jazzteam.test.model.login;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Savenok.Ivan on 05.04.2017.
 */
public class SignInPage extends BaseSignInPage {

    @FindBy(css = "#email")
    private WebElement emailField;


    @FindBy(css = "#social--signup--submit")
    private WebElement registerButton;

    @FindBy(css = "#password")
    private WebElement passwordField;

    @Override
    public WebElement getEmail() {
        return emailField;
    }

    @Override
    public WebElement getSubmitButton() {
        return registerButton;
    }

    @Override
    public WebElement getPassword() {
        return null;
    }
}

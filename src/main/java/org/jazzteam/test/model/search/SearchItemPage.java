package org.jazzteam.test.model.search;

import org.jazzteam.test.model.AbstractBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

/**
 * Created by Savenok.Ivan on 10.04.2017.
 */

@Component
@FindBy(className = " vehicle")
public class SearchItemPage extends AbstractBase {
    public static final String SAVED = "Saved";

    @FindBy(css = ".js-save-advert span")
    private WebElement saved;

    public void clickSaved(){
        if (getDriverExtension().isExistsReturnWebElement(saved) != null) {
            getDriverExtension().safeClickElementTopLeft(saved);
        }
    }

    public Boolean isCheccked(){
        if (getDriverExtension().isExistsReturnWebElement(saved) != null) {
            return saved.getText().equals(SAVED);
        }
        return null;
    }
}

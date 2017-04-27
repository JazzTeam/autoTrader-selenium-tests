package org.jazzteam.test.model.search;

import org.jazzteam.test.model.AbstractBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

/**
 * Created by Savenok.Ivan on 10.04.2017.
 */
@Component("SearchItem")
public class SearchItem extends AbstractBase {

    @FindBy(css = ".information-container a")
    private WebElement title;

    public void clickOnTitleItem() {
        if (getDriverExtension().isExistsReturnWebElement(title) != null) {
            getDriverExtension().safeClickElementTopLeft(title);
        }
    }

    public String getTitle() {
        if (getDriverExtension().isExistsReturnWebElement(title) != null) {
            return title.getText();
        }
        return null;
    }
}

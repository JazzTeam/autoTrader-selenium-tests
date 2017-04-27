package org.jazzteam.test.model.common.component;

import org.jazzteam.test.model.AbstractBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Savenok.Ivan on 10.04.2017.
 */

@Component("ComboBox")
public class ComboBox extends AbstractBase {

    @FindBy(css = "option")
    private List<WebElement> listComboBoxValue;

    @FindBy(css = "select")
    private WebElement buttonDropDownComboBox;

    /**
     * Select item by name
     *
     * @param value
     */
    public void selectValue(String value) {
        getDriverExtension().safeClickElementTopLeft(buttonDropDownComboBox);
        for (WebElement element: listComboBoxValue){
            if (element.getText().contains(value)){
                getDriverExtension().safeClickElement(element);
                break;
            }
        }
    }
}

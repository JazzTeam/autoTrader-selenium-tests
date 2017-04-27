package org.jazzteam.test.model.search;

import org.jazzteam.test.model.AbstractBase;
import org.jazzteam.test.model.common.component.ComboBox;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

/**
 * Created by Savenok.Ivan on 06.04.2017.
 */

@Component
@FindBy(className = "global__quicksearchform--form")
public class SearchBar extends AbstractBase {

    @FindBy(css = ".global__quicksearchform--make .global__form--border")
    private ComboBox makeCombobox;

    @FindBy(css = ".global__quicksearchform--model .global__form--border")
    private ComboBox modelCombobox;

    @FindBy(css = ".global__quicksearchform--pricemin .global__form--border")
    private ComboBox minPriceCombobox;

    @FindBy(css = ".global__quicksearchform--pricemax .global__form--border")
    private ComboBox maxPriceCombobox;

    @FindBy(className = "button-subtext")
    private WebElement searchButton;

    @FindBy(id = "postcode")
    private WebElement postcodeField;


    public void setMakeCombobox(String make) {
            makeCombobox.selectValue( make);
    }

    public void setModelCombobox(String model) {
        modelCombobox.selectValue( model);
    }

    public void setMinPriceCombobox(String price) {
        minPriceCombobox.selectValue( price);
    }

    public void setMaxPriceCombobox(String price) {
            maxPriceCombobox.selectValue(price);
    }

    public void clickSearchButton(){
        if (getDriverExtension().isExistsReturnWebElement(searchButton) != null) {
            getDriverExtension().safeClickElementTopLeft(searchButton);
        }
    }

    public void setPostcode(String postcode){
        if (getDriverExtension().isExistsReturnWebElement(postcodeField) != null) {
            getDriverExtension().safeEnterText(postcodeField, postcode);
        }
    }
}

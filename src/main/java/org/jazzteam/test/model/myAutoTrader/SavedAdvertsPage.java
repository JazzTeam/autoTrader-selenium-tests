package org.jazzteam.test.model.myAutoTrader;

import org.jazzteam.test.model.AbstractBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Savenok.Ivan on 17.04.2017.
 */

@Component
@FindBy(id = "my-profile-content")
public class SavedAdvertsPage extends AbstractBase {

    public static final String DELETE_WEBELEMENT_SELECTOR = "js--myautotrader-savedadvert-delete-advert";

    @FindBy(className = "myautotrader-savedsearches--vehicle--listitem")
    private List<WebElement> carList;

    public void deleteCarByName(String name) {
        for (WebElement element: carList){
            if (element.getText().toLowerCase().contains(name.toLowerCase())){
                getDriverExtension().safeClickElement(element.findElement(By.className(DELETE_WEBELEMENT_SELECTOR)));
                getDriverExtension().waiting(WAIT_PERIOD);
                break;
            }
        }
    }

    public boolean isExistCar(String name) {
        for (WebElement element: carList){
            if (element.getText().toLowerCase().contains(name.toLowerCase())){
                return true;
            }
        }
        return false;
    }
}

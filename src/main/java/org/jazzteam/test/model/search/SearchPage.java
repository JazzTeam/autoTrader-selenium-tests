package org.jazzteam.test.model.search;

import org.jazzteam.test.model.AbstractBase;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

/**
 * Created by Savenok.Ivan on 04.04.2017.
 */

@Component
@FindBy(css = ".global__quicksearchform--form")
public class SearchPage  extends AbstractBase {

    public static final String FEEDBACK_DIALOG_SELECTOR = "fsrOverlay";

    public boolean isExistFeedbackDialog(){
        if(getDriverExtension().getWrappedDriver().findElements(By.id(FEEDBACK_DIALOG_SELECTOR) ).size() != 0){
            return true;
        }
        return false;
    }

}

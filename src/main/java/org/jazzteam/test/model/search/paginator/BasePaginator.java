package org.jazzteam.test.model.search.paginator;

import org.jazzteam.test.model.AbstractBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * Created by Savenok.Ivan on 08.04.2017.
 */
@Component("Paginator")
public class BasePaginator extends AbstractBase {

    @FindBy(css = ".pagination--li a")
    private List<WebElement> pageList;

    @FindBy(className = "pagination--left__active")
    private WebElement leftPaginationButton;

    @FindBy(className = "pagination--right__active")
    private WebElement rightPaginationButton;

    public void clickPageByNumber(String value) {
        for (WebElement element: pageList){
            if (element.getText().contains(value)){
                getDriverExtension().safeClickElementTopRight(element);
                getDriverExtension().waiting(WAIT_PERIOD);
                break;
            }
        }
    }

    public void previousPage() {
        if (getDriverExtension().isExistsReturnWebElement(leftPaginationButton) != null) {
            getDriverExtension().safeClickElement(leftPaginationButton);
        }
    }

    public void nextPage() {
        if (getDriverExtension().isExistsReturnWebElement(rightPaginationButton) != null) {
            getDriverExtension().safeClickElement(rightPaginationButton);
        }
    }


}

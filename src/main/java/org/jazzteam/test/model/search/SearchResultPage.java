package org.jazzteam.test.model.search;

import org.jazzteam.test.model.AbstractBase;
import org.jazzteam.test.model.search.paginator.BasePaginator;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Savenok.Ivan on 10.04.2017.
 */

@Component
@FindBy(className = "js-search-results")
public class SearchResultPage  extends AbstractBase {


    @FindBy(className = "pagination")
    private BasePaginator paginator;

    @FindBy(className = "search-page__result")
    private List<SearchItem> searchItems;

    public void clickPageByNumber(String pageNumber) {
        paginator.clickPageByNumber(pageNumber);
    }

    public SearchItem getSearchItemByName(String itemName) {
        for (SearchItem searchItem: searchItems){
            if (searchItem.getTitle().indexOf(itemName) >= 0){
                return searchItem;
            }
        }
        return null;
    }

    public void doOpenSearchItemPageByName(String itemName) {
        getSearchItemByName(itemName).clickOnTitleItem();
        getDriverExtension().waiting(WAIT_PERIOD);
    }

    public void doOpenSearchItemPageByIndex(int itemIndex) {
        SearchItem searchItem = searchItems.get(itemIndex);
        searchItem.clickOnTitleItem();
        getDriverExtension().waiting(WAIT_PERIOD);
    }

    public String getItemNameByIndex(int itemIndex) {
        SearchItem searchItem = searchItems.get(itemIndex);
        return searchItem.getTitle();
    }

    public void nextPage() {
        paginator.nextPage();
    }

    public void previousPage() {
        paginator.previousPage();
    }
}

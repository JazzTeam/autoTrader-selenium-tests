package org.jazzteam.test.steps.search;

import org.jazzteam.test.model.search.SearchItemPage;
import org.springframework.stereotype.Component;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by Savenok.Ivan on 10.04.2017.
 */

@Component
public class SearchItemSteps {

    private static final ThreadLocal<SearchItemPage> SEARCH_ITEM_PAGE_THREAD_LOCAL = new ThreadLocal<>();

    public SearchItemPage getSearchItemPage() {
        return SEARCH_ITEM_PAGE_THREAD_LOCAL.get();
    }

    public void setSearchItemPage(SearchItemPage searchItemPage) {
        SEARCH_ITEM_PAGE_THREAD_LOCAL.set(searchItemPage);
    }

    public void savedCar(){
        if(!getSearchItemPage().isCheccked())
            getSearchItemPage().clickSaved();
    }

    public void removeSavedCar(){
        if(getSearchItemPage().isCheccked())
            getSearchItemPage().clickSaved();
    }

    public void checkSavedCar(String carName, boolean isExist){
        boolean result = getSearchItemPage().isCheccked();
        assertThat( "Car  " + carName + (isExist ? " not" : " ") + " saved in savedList",result, is(isExist));
    }
}

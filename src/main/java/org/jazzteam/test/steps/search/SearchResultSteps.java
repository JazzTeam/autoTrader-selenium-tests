package org.jazzteam.test.steps.search;

import org.hamcrest.Matchers;
import org.jazzteam.test.config.ApplicationContextUtils;
import org.jazzteam.test.model.AbstractBase;
import org.jazzteam.test.model.search.SearchItemPage;
import org.jazzteam.test.model.search.SearchResultPage;
import org.springframework.stereotype.Component;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Savenok.Ivan on 10.04.2017.
 */

@Component
public class SearchResultSteps {
    private static final ThreadLocal<SearchResultPage> SEARCH_RESULT_THREAD_LOCAL = new ThreadLocal<>();

    public SearchResultPage getSearchResultPage() {
        return SEARCH_RESULT_THREAD_LOCAL.get();
    }

    public void setSearchBarThreadLocal(SearchResultPage searchResultPage) {
        SEARCH_RESULT_THREAD_LOCAL.set(searchResultPage);
    }

    public void goToPageByNumber(String pageNumber) {
        getSearchResultPage().handleFeedbackDialog();
        getSearchResultPage().clickPageByNumber(pageNumber);
    }

    public void nextPage() {
        getSearchResultPage().nextPage();
    }

    public SearchItemSteps doOpenSearchItemPageByName(String itemName) {
        getSearchResultPage().handleFeedbackDialog();
        getSearchResultPage().doOpenSearchItemPageByName(itemName);

        SearchItemSteps searchItemSteps =  ApplicationContextUtils.getBean(SearchItemSteps.class);
        assertThat("SearchItemSteps was not create", searchItemSteps, Matchers.notNullValue());
        searchItemSteps.setSearchItemPage(AbstractBase.getInitPageBean(SearchItemPage.class));
        return searchItemSteps;
    }

    public SearchItemSteps doOpenSearchItemPageByIndex(int itemIndex) {
        getSearchResultPage().doOpenSearchItemPageByIndex(itemIndex);

        SearchItemSteps searchItemSteps =  ApplicationContextUtils.getBean(SearchItemSteps.class);
        assertThat("SearchItemSteps was not create", searchItemSteps, Matchers.notNullValue());
        searchItemSteps.setSearchItemPage(AbstractBase.getInitPageBean(SearchItemPage.class));
        return searchItemSteps;
    }

    public String getCarNameByIndex(int itemIndex) {
        getSearchResultPage().handleFeedbackDialog();
        return getSearchResultPage().getItemNameByIndex(itemIndex);
    }

    public void previousPage() {
        getSearchResultPage().previousPage();
    }

}

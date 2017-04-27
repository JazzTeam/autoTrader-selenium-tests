package org.jazzteam.test.steps.search;

import org.hamcrest.Matchers;
import org.jazzteam.test.config.ApplicationContextUtils;
import org.jazzteam.test.model.AbstractBase;
import org.jazzteam.test.model.search.SearchBar;
import org.jazzteam.test.model.search.SearchResultPage;
import org.springframework.stereotype.Component;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Savenok.Ivan on 06.04.2017.
 */


@Component
public class SearchBarSteps {

    private static final ThreadLocal<SearchBar> SEARCH_BAR_THREAD_LOCAL = new ThreadLocal<>();

    public SearchBar getSearchBar() {
        return SEARCH_BAR_THREAD_LOCAL.get();
    }

    public void setSearchBar(SearchBar searchBar) {
        SEARCH_BAR_THREAD_LOCAL.set(searchBar);
    }

    public SearchBarSteps setMakeCombobox(String make) {
        getSearchBar().setMakeCombobox(make);
        return this;
    }

    public SearchBarSteps setModelCombobox(String model) {
        getSearchBar().setModelCombobox(model);
        return this;
    }

    public SearchBarSteps setMinPriceCombobox(String price) {
        getSearchBar().setMinPriceCombobox(price);
        return this;
    }

    public SearchBarSteps setMaxPriceCombobox(String price) {
        getSearchBar().setMaxPriceCombobox(price);
        return this;
    }

    public SearchResultSteps clickSearchButton(){
        getSearchBar().clickSearchButton();

        SearchResultSteps searchResultSteps = ApplicationContextUtils.getBean(SearchResultSteps.class);
        assertThat("SearchResultSteps was not create", searchResultSteps, Matchers.notNullValue());
        searchResultSteps.setSearchBarThreadLocal(AbstractBase.getInitPageBean(SearchResultPage.class));
        return searchResultSteps;

    }

    public SearchBarSteps setPostcode(String postcode){
        getSearchBar().setPostcode(postcode);
        return this;
    }
}

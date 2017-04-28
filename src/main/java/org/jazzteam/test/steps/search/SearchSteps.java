package org.jazzteam.test.steps.search;

import org.hamcrest.Matchers;
import org.jazzteam.test.config.ApplicationContextUtils;
import org.jazzteam.test.model.AbstractBase;
import org.jazzteam.test.model.common.dialog.PopUpModalDialog;
import org.jazzteam.test.model.search.SearchBar;
import org.jazzteam.test.model.search.SearchPage;
import org.jazzteam.test.steps.dialog.PopUpModalDialogSteps;
import org.springframework.stereotype.Component;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Savenok.Ivan on 04.04.2017.
 */

@Component
public class SearchSteps {
    private static final ThreadLocal<SearchPage> SEARCH_PAGE_THREAD_LOCAL = new ThreadLocal<>();

    public SearchPage getSearchPage() {
        return SEARCH_PAGE_THREAD_LOCAL.get();
    }

    public void setSearchPage(SearchPage headerPage) {
        SEARCH_PAGE_THREAD_LOCAL.set(headerPage);
    }

    public SearchBarSteps getSearchBar() {
        SearchBarSteps searchBarSteps = ApplicationContextUtils.getBean(SearchBarSteps.class);
        assertThat("SearchBar was not create", searchBarSteps, Matchers.notNullValue());
        searchBarSteps.setSearchBar(AbstractBase.getInitPageBean(SearchBar.class));
        return searchBarSteps;
    }

    public SearchResultSteps searchWithParam(String postcode, String carMark, String carModel) {
        return getSearchBar()
                .setPostcode(postcode)
                .setMakeCombobox(carMark)
                .setModelCombobox(carModel)
                .clickSearchButton();
    }

    public PopUpModalDialogSteps openModalDialog() {
        PopUpModalDialogSteps modalDialogSteps = ApplicationContextUtils.getBean(PopUpModalDialogSteps.class);
        assertThat("PopUpModalDialogSteps was not create", modalDialogSteps, Matchers.notNullValue());
        modalDialogSteps.setPopUpModalDialog(AbstractBase.getInitPageBean(PopUpModalDialog.class));
        return modalDialogSteps;
    }

    public void handleModalDialog(){
        openModalDialog().closeWindow();
    }
}

package org.jazzteam.test.search;

import org.jazzteam.test.BaseWebDriverTest;

import org.jazzteam.test.steps.myAutoTrader.SavedAdvertsSteps;
import org.jazzteam.test.steps.myAutoTrader.dialog.PopUpRemoveDialogSteps;
import org.jazzteam.test.steps.search.SearchItemSteps;
import org.jazzteam.test.steps.search.SearchResultSteps;
import org.jazzteam.test.steps.search.SearchSteps;
import org.jazzteam.test.steps.login.LoginSteps;
import org.jazzteam.test.user.User;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by Savenok.Ivan on 10.04.2017.
 */

public class SearchTabTest extends BaseWebDriverTest {

    @BeforeClass
    public void setUp(){
        headerSteps.openStartPage();
        LoginSteps loginSteps = headerSteps.openLoginPage();
        loginSteps.loginInApp(User.SIMPLE_USER);
    }

    @Test
    public void shouldBeFindCarLikeItAndRemoveLikeInProfile() {
        int carNumber = 2;
        String pageNumber = "3";
        String carMark = "Volkswagen";
        String carModel = "Golf";
        String postcode = "SK2 6SJ";
        String savedCareName = carMark + " " + carModel;

        // Find car by mark and model
        SearchSteps searchSteps = headerSteps.goToSearchPage();
        SearchResultSteps searchResultSteps = searchSteps.searchWithParam(postcode, carMark, carModel);
        // To handle appeared modal dialog appearing after search
        searchSteps.handleModalDialog();

        // Go to page number 3
        searchResultSteps.goToPageByNumber(pageNumber);
        // Find car by number 2, and go to its page
        String carName = searchResultSteps.getCarNameByIndex(carNumber);
        SearchItemSteps searchItemSteps = searchResultSteps.doOpenSearchItemPageByName(carName);
        // Like car
        searchItemSteps.savedCar();

        // Go to "My Auto Trader"
        SavedAdvertsSteps savedAdvertsSteps = headerSteps.goToMyAutoTradePage().goToSavedAdvertsPage();
        // Make sure that car is like
        savedAdvertsSteps.checkCarInSavedList(savedCareName, AVAILABLE);
        // Removed like
        PopUpRemoveDialogSteps removeDialogSteps = savedAdvertsSteps.deleteCarByName(savedCareName);
        removeDialogSteps.confirm();
        savedAdvertsSteps.checkCarInSavedList(savedCareName, UNAVAILABLE);

        // Find car by mark and model
        searchSteps = headerSteps.goToSearchPage();
        searchResultSteps  = searchSteps.searchWithParam(postcode, carMark, carModel);
        // Make sure that car does not have like
        searchResultSteps.goToPageByNumber(pageNumber);
        searchItemSteps = searchResultSteps.doOpenSearchItemPageByName(carName);
        searchItemSteps.checkSavedCar(carName, UNAVAILABLE);
    }
}

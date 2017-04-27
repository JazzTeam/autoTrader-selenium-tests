package org.jazzteam.test.steps.myAutoTrader;

import org.hamcrest.Matchers;
import org.jazzteam.test.config.ApplicationContextUtils;
import org.jazzteam.test.model.AbstractBase;
import org.jazzteam.test.model.myAutoTrader.SavedAdvertsPage;
import org.jazzteam.test.model.myAutoTrader.dialog.PopUpRemoveDialog;
import org.jazzteam.test.steps.myAutoTrader.dialog.PopUpRemoveDialogSteps;
import org.springframework.stereotype.Component;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by Savenok.Ivan on 17.04.2017.
 */
@Component
public class SavedAdvertsSteps {
    private static final ThreadLocal<SavedAdvertsPage> SAVED_ADVERTS_PAGE_THREAD_LOCAL = new ThreadLocal<>();

    public SavedAdvertsPage getSavedAdvertsPage() {
        return SAVED_ADVERTS_PAGE_THREAD_LOCAL.get();
    }

    public void setSavedAdvertsPage(SavedAdvertsPage savedAdvertsPage) {
        SAVED_ADVERTS_PAGE_THREAD_LOCAL.set(savedAdvertsPage);
    }

    public void checkCarInSavedList(String carName, boolean isExist){
        boolean result = getSavedAdvertsPage().isExistCar(carName);
        assertThat( "Car  " + carName + (isExist ? " not" : " ") + " include in savedList",result, is(isExist));
    }

    public PopUpRemoveDialogSteps deleteCarByName(String carName){
        getSavedAdvertsPage().deleteCarByName(carName);

        PopUpRemoveDialogSteps modalDialogSteps = ApplicationContextUtils.getBean(PopUpRemoveDialogSteps.class);
        assertThat("PopUpModalDialogSteps was not create", modalDialogSteps, Matchers.notNullValue());
        modalDialogSteps.setPopupRemoveDialog(AbstractBase.getInitPageBean(PopUpRemoveDialog.class));
        return modalDialogSteps;
    }
}

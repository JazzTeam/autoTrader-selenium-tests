package org.jazzteam.test.steps.myAutoTrader;

import org.hamcrest.Matchers;
import org.jazzteam.test.config.ApplicationContextUtils;
import org.jazzteam.test.model.AbstractBase;
import org.jazzteam.test.model.myAutoTrader.MyAutoTradePage;
import org.jazzteam.test.model.myAutoTrader.SavedAdvertsPage;
import org.springframework.stereotype.Component;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Savenok.Ivan on 17.04.2017.
 */
@Component
public class MyAutoTradeSteps {

    private static final ThreadLocal<MyAutoTradePage> MY_AUTO_TRADE_PAGE_THREAD_LOCAL = new ThreadLocal<>();

    public MyAutoTradePage getMyAutoTradePage() {
        return MY_AUTO_TRADE_PAGE_THREAD_LOCAL.get();
    }

    public void setMyAutoTradePage(MyAutoTradePage myAutoTradePage) {
        MY_AUTO_TRADE_PAGE_THREAD_LOCAL.set(myAutoTradePage);
    }

    public SavedAdvertsSteps goToSavedAdvertsPage() {
        getMyAutoTradePage().doOpenSavedAdverts();
        SavedAdvertsSteps savedAdvertsSteps =  ApplicationContextUtils.getBean(SavedAdvertsSteps.class);
        assertThat("SavedAdvertsSteps was not create", savedAdvertsSteps, Matchers.notNullValue());
        savedAdvertsSteps.setSavedAdvertsPage(AbstractBase.getInitPageBean(SavedAdvertsPage.class));
        return savedAdvertsSteps;
    }
}

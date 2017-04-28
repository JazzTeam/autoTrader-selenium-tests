package org.jazzteam.test.steps.header;
import org.hamcrest.Matchers;
import org.jazzteam.test.config.ApplicationContextUtils;
import org.jazzteam.test.config.ConfigurationProperties;
import org.jazzteam.test.core.DriverManager;
import org.jazzteam.test.model.AbstractBase;
import org.jazzteam.test.model.header.HeaderPage;
import org.jazzteam.test.model.myAutoTrader.MyAutoTradePage;
import org.jazzteam.test.model.search.SearchPage;
import org.jazzteam.test.model.login.SignUpPage;
import org.jazzteam.test.steps.myAutoTrader.MyAutoTradeSteps;
import org.jazzteam.test.steps.search.SearchSteps;
import org.jazzteam.test.steps.login.LoginSteps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;


/**
 * Created by Savenok.Ivan on 04.04.2017.
 */
@Component
public class HeaderSteps {

    private static final Logger logger = LoggerFactory.getLogger(HeaderSteps.class);

    private static final ThreadLocal<HeaderPage> HEADER_PAGE_THREAD_LOCAL = new ThreadLocal<>();

    private HeaderPage getHeaderPage() {
        return HEADER_PAGE_THREAD_LOCAL.get();
    }

    private void setHeaderPage(HeaderPage headerPage) {
        HEADER_PAGE_THREAD_LOCAL.set(headerPage);
    }

    public void openStartPage() {
        openPage(ConfigurationProperties.getInstance().getEndpoint());
    }

    /**
     * Open start page by url
     *
     * @param startUrl
     */
    private void openPage(String startUrl) {
        logger.info("Opening URL:  " + startUrl);
        DriverManager driverManager = ApplicationContextUtils.getBean(DriverManager.class);
        driverManager.getDriverExtension()
                .getWrappedDriver()
                .manage()
                .timeouts()
                .pageLoadTimeout(ConfigurationProperties.getInstance().getTimeoutPageLoad(), TimeUnit.SECONDS);
        driverManager.getDriverExtension().open(startUrl);
        setHeaderPage(AbstractBase.getInitPageBean(HeaderPage.class));
    }

    public SearchSteps goToSearchPage() {
        getHeaderPage().doOpenSearch();
        SearchSteps searchSteps =  ApplicationContextUtils.getBean(SearchSteps.class);
        assertThat("SearchSteps was not create", searchSteps, Matchers.notNullValue());
        searchSteps.setSearchPage(AbstractBase.getInitPageBean(SearchPage.class));
        return searchSteps;
    }

    public LoginSteps openLoginPage() {
        getHeaderPage().doOpenLoginPage();
        LoginSteps loginSteps = ApplicationContextUtils.getBean(LoginSteps.class);
        assertThat("LoginSteps was not create", loginSteps, Matchers.notNullValue());
        loginSteps.setPage(AbstractBase.getInitPageBean(SignUpPage.class));
        return loginSteps;
    }

    public MyAutoTradeSteps goToMyAutoTradePage() {
        getHeaderPage().doOpenMyAutoTradePage();
        MyAutoTradeSteps myAutoTradeSteps =  ApplicationContextUtils.getBean(MyAutoTradeSteps.class);
        assertThat("MyAutoTradeSteps was not create", myAutoTradeSteps, Matchers.notNullValue());
        myAutoTradeSteps.setMyAutoTradePage(AbstractBase.getInitPageBean(MyAutoTradePage.class));
        return myAutoTradeSteps;
    }

}

package org.jazzteam.test.steps.login;
import org.jazzteam.test.model.login.BaseSignInPage;


/**
 * Created by Savenok.Ivan on 05.04.2017.
 */
public abstract class BaseRegisterSteps <T extends BaseSignInPage>{

    private final ThreadLocal<T> PAGE_THREAD_LOCAL = new ThreadLocal<>();

    public T getPage() {
        return PAGE_THREAD_LOCAL.get();
    }

    public void setPage(T page) {
        PAGE_THREAD_LOCAL.set(page);
    }

    protected void setUserDataAndSubmit(String email, String password){
        getPage().setEmail(email);
        getPage().setPassword(password);
        getPage().clickSubmit();
    }
}

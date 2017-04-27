package org.jazzteam.test.steps.login;

import org.jazzteam.test.user.User;
import org.springframework.stereotype.Component;

/**
 * Created by Savenok.Ivan on 05.04.2017.
 */
@Component
public class LoginSteps extends BaseRegisterSteps{

    public void loginInApp(User user){
        setUserDataAndSubmit(user.getLogin(), user.getPassword());
    }

}

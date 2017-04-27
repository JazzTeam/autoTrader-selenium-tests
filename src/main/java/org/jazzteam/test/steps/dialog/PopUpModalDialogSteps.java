package org.jazzteam.test.steps.dialog;

import org.jazzteam.test.model.common.dialog.PopUpModalDialog;
import org.springframework.stereotype.Component;

/**
 * Created by Savenok.Ivan on 08.04.2017.
 */
@Component
public class PopUpModalDialogSteps {

    private static final ThreadLocal<PopUpModalDialog> POPUP_MODAL_DIALOG_THREAD_LOCAL = new ThreadLocal<>();

    public PopUpModalDialog getPopUpModalDialog() {
        return POPUP_MODAL_DIALOG_THREAD_LOCAL.get();
    }

    public void setPopUpModalDialog(PopUpModalDialog popUpModalDialog) {
        POPUP_MODAL_DIALOG_THREAD_LOCAL.set(popUpModalDialog);
    }

    public void closeWindow() {
        getPopUpModalDialog().closeWindow();
        setPopUpModalDialog(null);
    }
}

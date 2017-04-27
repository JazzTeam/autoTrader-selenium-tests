package org.jazzteam.test.steps.myAutoTrader.dialog;

import org.jazzteam.test.model.myAutoTrader.dialog.PopUpRemoveDialog;
import org.springframework.stereotype.Component;

/**
 * Created by Savenok.Ivan on 17.04.2017.
 */

@Component
public class PopUpRemoveDialogSteps {
    private static final ThreadLocal<PopUpRemoveDialog> POPUP_REMOVE_DIALOG_THREAD_LOCAL = new ThreadLocal<>();

    public PopUpRemoveDialog getPopUpRemoveDialog() {
        return POPUP_REMOVE_DIALOG_THREAD_LOCAL.get();
    }

    public void setPopupRemoveDialog(PopUpRemoveDialog removeDialog) {
        POPUP_REMOVE_DIALOG_THREAD_LOCAL.set(removeDialog);
    }

    public void confirm() {
        getPopUpRemoveDialog().confirm();
        setPopupRemoveDialog(null);
    }
}

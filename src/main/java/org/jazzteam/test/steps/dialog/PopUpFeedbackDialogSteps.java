package org.jazzteam.test.steps.dialog;

import org.jazzteam.test.model.common.dialog.PopUpFeedbackDialog;
import org.springframework.stereotype.Component;

/**
 * Created by Savenok.Ivan on 26.04.2017.
 */

@Component
public class PopUpFeedbackDialogSteps {

    private static final ThreadLocal<PopUpFeedbackDialog> POPUP_FEEDBACK_DIALOG_THREAD_LOCAL = new ThreadLocal<>();

    public PopUpFeedbackDialog getUpFeedbackDialog() {
        return POPUP_FEEDBACK_DIALOG_THREAD_LOCAL.get();
    }

    public void setPopUpFeedbackDialog(PopUpFeedbackDialog popUpFeedbackDialog) {
        POPUP_FEEDBACK_DIALOG_THREAD_LOCAL.set(popUpFeedbackDialog);
    }

    /**
     * Close PopUpFeedbackDialog
     */
    public void closeWindow() {
        getUpFeedbackDialog().closeWindow();
        setPopUpFeedbackDialog(null);
    }
}

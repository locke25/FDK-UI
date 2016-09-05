package com.jcim.fdk.ui.model.action;


/**
 * Thrown if an {@link Action} fails its execution.
 */
public class ActionExecutionException extends Exception {

    public ActionExecutionException() {
    }

    public ActionExecutionException(String message) {
        super(message);
    }

    public ActionExecutionException(Throwable cause) {
        super(cause);
    }

    public ActionExecutionException(String message, Throwable cause) {
        super(message, cause);
    }

}

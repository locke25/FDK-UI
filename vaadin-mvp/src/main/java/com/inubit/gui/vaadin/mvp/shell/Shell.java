package com.inubit.gui.vaadin.mvp.shell;

import com.inubit.gui.vaadin.mvp.event.HandlerRegistration;

/**
 * Decouples the presenters and the Vaadin application. Provides methods to show messages and configuration dialogs.
 */
public interface Shell {

    void askForConfirmation(String message, ConfirmationHandler listener);

    void showNotification(String message);

    void showError(String message, Exception e);
    /**
     * Opens the given resource in a window with the given name.
     * <p>
     * The supplied {@code windowName} is used as the target name in a
     * window.open call in the client. This means that special values such as
     * "_blank", "_self", "_top", "_parent" have special meaning. An empty or
     * <code>null</code> window name is also a special case.
     * </p>
     * <p>
     * "", null and "_self" as {@code windowName} all causes the resource to be
     * opened in the current window, replacing any old contents. For
     * downloadable content you should avoid "_self" as "_self" causes the
     * client to skip rendering of any other changes as it considers them
     * irrelevant (the page will be replaced by the resource). This can speed up
     * the opening of a resource, but it might also put the client side into an
     * inconsistent state if the window content is not completely replaced e.g.,
     * if the resource is downloaded instead of displayed in the browser.
     * </p>
     * <p>
     * "_blank" as {@code windowName} causes the resource to always be opened in
     * a new window or tab (depends on the browser and browser settings).
     * </p>
     * <p>
     * "_top" and "_parent" as {@code windowName} works as specified by the HTML
     * standard.
     * </p>
     * <p>
     * Any other {@code windowName} will open the resource in a window with that
     * name, either by opening a new window/tab in the browser or by replacing
     * the contents of an existing window with that name.
     * </p>
     *
     */
    // FIXME
//    void openWindow(String uri, String windowName);

    // FIXME the following methods should be encapsulated into an specific interface: FragmentUtility or so
    String getFragment();

    void setFragment(String fragment);

    HandlerRegistration addFragmentChangedHandler(FragmentChangedHandler handler);

    Shell createSubShell(String id);


}

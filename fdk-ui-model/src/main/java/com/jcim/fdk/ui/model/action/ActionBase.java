package com.jcim.fdk.ui.model.action;

/**
 * An {@link Action} taking an {@link ActionDefinition}.
 * @param <D> the definition type
 */
public abstract class ActionBase<D extends ActionDefinition> implements Action {

    private D definition;

    public ActionBase(D definition) {
        this.definition = definition;
    }

    protected D getDefinition() {
        return definition;
    }

}

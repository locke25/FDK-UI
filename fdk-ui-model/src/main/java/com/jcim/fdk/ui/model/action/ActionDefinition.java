package com.jcim.fdk.ui.model.action;

/**
 * An action definition is always associated with an {@link Action} and provides
 * the latter with the context (i.e. dependencies) it needs to be executed. For
 * instance, a place change action might provide a place object to move to via a
 * place controller. An action definition is also used by {@link ActionFactory}
 * implementations to look up and retrieve the associated Action.
 * Implementations are expected to provide correct {@link Object#equals(Object)}
 * and {@link Object#hashCode()} methods.
 */
public interface ActionDefinition {

}

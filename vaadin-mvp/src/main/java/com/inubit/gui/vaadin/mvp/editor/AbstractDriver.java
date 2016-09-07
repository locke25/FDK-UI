package com.inubit.gui.vaadin.mvp.editor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Abstract support class for classes implementing the Driver interface.
 *
 * @param <T> the type of entity to edit
 * @version $Id$
 */
public abstract class AbstractDriver<T> implements Driver<T> {

    /**
     * Trivial implementation of EditorError.
     *
     * @version $Id$
     */
    private static class SimpleEditorError implements EditorError {

        private String path;
        private Editor editor;
        private String message;
        private Object value;
        private boolean consumed;

        private SimpleEditorError(String path, Editor editor, String message, Object value) {
            this.path = path;
            this.editor = editor;
            this.message = message;
            this.value = value;
        }

        @Override
        public String getPath() {
            return path;
        }

        @Override
        public Editor getEditor() {
            return editor;
        }

        @Override
        public String getMessage() {
            return message;
        }

        @Override
        public Object getValue() {
            return value;
        }

        @Override
        public boolean isConsumed() {
            return consumed;
        }

        @Override
        public void setConsumed(boolean consumed) {
            this.consumed = consumed;
        }
    }

    /**
     * Interface for use by subclasses when iterating the editor hierarchy.
     */
    protected static interface EditorVisitor {
        void visit(Editor editor);
    }

    private List<EditorError> errors = new ArrayList<EditorError>();
    private HasEditors view;

    @Override
    public void initialize(HasEditors view) {
        this.view = view;

        // TODO should this really happen here?

        visitEditors(view, new EditorVisitor() {
            @Override
            public void visit(final Editor editor) {
                if ((editor instanceof HasEditorDelegate)) {
                    ((HasEditorDelegate) editor).setDelegate(new EditorDelegate() {
                        @Override
                        public void recordError(String message, Object value) {
                            String path = editor instanceof HasEditorPath ? ((HasEditorPath) editor).getPath() : "";
                            addError(path, editor, message, value);
                        }
                    });
                }
            }
        });
    }

    protected void addError(String path, Editor editor, String message, Object value) {
        this.errors.add(new SimpleEditorError(path, editor, message, value));
    }

    protected void clearErrors() {
        this.errors.clear();
    }

    @Override
    public boolean hasErrors() {
        return !this.errors.isEmpty();
    }

    public boolean hasErrors(String path) {
        for (EditorError error : errors) {
            if (error.getPath().equals(path)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<EditorError> getErrors() {
        ArrayList<EditorError> unconsumedErrors = new ArrayList<EditorError>();
        for (EditorError error : errors) {
            if (!error.isConsumed()) {
                unconsumedErrors.add(error);
            }
        }
        return unconsumedErrors;
    }

    public List<EditorError> getAllErrors() {
        return Collections.unmodifiableList(errors);
    }

    protected HasEditors getView() {
        return view;
    }

    /**
     * Iterates an editor hierarchy in a depth-first bottom-up fashion.
     *
     * @param hasEditors editor to start iterating at
     * @param visitor    visitor to invoke for each editor
     */
    protected void visitEditors(HasEditors hasEditors, EditorVisitor visitor) {
        for (Editor editor : hasEditors.getEditors()) {
            if (editor instanceof HasEditors) {
                visitEditors((HasEditors) editor, visitor);
            }
            visitor.visit(editor);
        }
    }
}

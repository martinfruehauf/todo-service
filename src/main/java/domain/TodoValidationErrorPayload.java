package domain;

import infrastructure.rest.error.ValidationErrorPayload;

public final class TodoValidationErrorPayload {

    public static class NegativeTodoId extends ValidationErrorPayload {
        public NegativeTodoId() {
            super("NEGATIVE_TODO_ID", "todoId must be greater than or equal to 0");
        }
    }

    public static class TitleIsInvalid extends ValidationErrorPayload {
        public TitleIsInvalid() {
            super("TITLE_NULL", "title must not be null");
        }
    }

    public static class TitleSize extends ValidationErrorPayload {
        public TitleSize() {
            super("TITLE_SIZE", "title size must be between 1 and 30");
        }
    }

    public static class DescriptionSize extends ValidationErrorPayload {
        public DescriptionSize() {
            super("DESCRIPTION_SIZE", "description size must be between 0 and 500");
        }
    }

    public static class DueDateNull extends ValidationErrorPayload {
        public DueDateNull() {
            super("DUEDATE_NULL", "dueDate must not be null");
        }
    }

    public static  class BaseTodoNull extends ValidationErrorPayload {
        public BaseTodoNull() {
            super("BASETODO_NULL", "baseTodo must not be null");
        }
    }



}

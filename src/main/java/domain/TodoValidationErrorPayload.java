package domain;

import infrastructure.rest.error.ValidationErrorPayload;

public final class TodoValidationErrorPayload {

    public static class NegativeTodoId extends ValidationErrorPayload {
        public NegativeTodoId() {
            super("NEGATIVE_TODO_ID", "todoId must be greater than or equal to 0");
        }
    }

    public static class LimitMinInvalid extends ValidationErrorPayload {
        public LimitMinInvalid() {
            super("LIMIT_MIN", "limit must be greater or equal to 0");
        }
    }

    public static class LimitMaxInvalid extends ValidationErrorPayload {
        public LimitMaxInvalid() {
            super("LIMIT_MAX", "limit must be less or equal to 10");
        }
    }

    public static class OffsetMinInvalid extends ValidationErrorPayload {
        public OffsetMinInvalid() {
            super("OFFSET_MIN", "offset must be greater or equal to 0");
        }
    }

    public static class OffsetMaxInvalid extends ValidationErrorPayload {
        public OffsetMaxInvalid() {
            super("OFFSET_MAX", "offset must be less or equal to 100");
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
}

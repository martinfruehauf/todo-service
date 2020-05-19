package infrastructure.adapters;

import javax.json.bind.adapter.JsonbAdapter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class LocalDateTimeAdapter implements JsonbAdapter<LocalDateTime, String> {

    @Override
    public String adaptToJson(final LocalDateTime dateTime) throws Exception {
        return dateTime.format(DateTimeFormatter.ISO_DATE_TIME);
    }

    @Override
    public LocalDateTime adaptFromJson(final String dateTime) throws Exception {
        LocalDateTime parsedDateTime;

        try {
            parsedDateTime = LocalDateTime.parse(dateTime);
        } catch (final DateTimeParseException e) {
            // You could throw another exception here for the validation.
            parsedDateTime = LocalDateTime.now();
        }

        return parsedDateTime;
    }
}

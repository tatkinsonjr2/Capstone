package sanctuaryraider.converters;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ZonedDateTimeConverter implements DynamoDBTypeConverter<String, ZonedDateTime> {

    @Override
    public String convert(final ZonedDateTime date){
        return date.format(DateTimeFormatter.ISO_DATE);
    }

    @Override
    public ZonedDateTime unconvert(final String stringValue){
        return ZonedDateTime.parse(stringValue);
    }

}

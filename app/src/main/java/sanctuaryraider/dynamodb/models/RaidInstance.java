package sanctuaryraider.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.time.ZonedDateTime;
import java.util.List;
@DynamoDBTable(tableName = "raids")
public class RaidInstance {

    private String raidName;
    private ZonedDateTime date;
    private String publicNote;
    private String officerNote;
    private String status;
    private String instanceName;
    private List<Character> attendees;

@DynamoDBAttribute(attributeName = "")

 }

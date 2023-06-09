package sanctuaryraider.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import com.fasterxml.jackson.annotation.JsonFormat;
import sanctuaryraider.converters.LocalDateConverter;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@DynamoDBTable(tableName = "raids")
public class Raid {

    private String raidName;
    private LocalDate date;
    private String publicNote;
    private String officerNote;
    private String status;
    private String instanceName;
    private Set<String> attendees;

    @DynamoDBHashKey(attributeName = "raidName")
    public String getRaidName() {
        return raidName;
    }

    public void setRaidName(String raidName) {
        this.raidName = raidName;
    }

    @DynamoDBTypeConverted(converter = LocalDateConverter.class)
    @DynamoDBAttribute
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    @DynamoDBAttribute(attributeName = "publicNote")
    public String getPublicNote() {
        return publicNote;
    }

    public void setPublicNote(String publicNote) {
        this.publicNote = publicNote;
    }
    @DynamoDBAttribute(attributeName = "officerNote")
    public String getOfficerNote() {
        return officerNote;
    }

    public void setOfficerNote(String officerNote) {
        this.officerNote = officerNote;
    }
    @DynamoDBAttribute(attributeName = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    @DynamoDBAttribute(attributeName = "instanceName")
    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }
    @DynamoDBAttribute(attributeName = "attendees")
    public Set<String> getAttendees() {
        return attendees;
    }

    public void setAttendees(Set<String> attendees) {
        this.attendees = attendees;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Raid raid = (Raid) o;
        return Objects.equals(raidName, raid.raidName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(raidName);
    }
}

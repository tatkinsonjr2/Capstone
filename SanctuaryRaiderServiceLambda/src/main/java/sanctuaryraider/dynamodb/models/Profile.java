package sanctuaryraider.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.List;
import java.util.Objects;

@DynamoDBTable(tableName = "profiles")
public class Profile {
    private String username;
    private String guild;
    private String publicNote;
    private String officerNote;

    @DynamoDBHashKey(attributeName = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @DynamoDBAttribute(attributeName = "guild")
    public String getGuild() {
        return guild;
    }

    public void setCharacters(String guild) {
        this.guild = guild;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Profile profile = (Profile) o;
        return Objects.equals(username, profile.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}

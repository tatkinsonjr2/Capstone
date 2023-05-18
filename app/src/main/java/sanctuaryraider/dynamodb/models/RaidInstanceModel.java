package sanctuaryraider.dynamodb.models;

import java.time.ZonedDateTime;
import java.util.List;

public class RaidInstanceModel {

    private String raidName;
    private ZonedDateTime date;
    private String publicNote;
    private String officerNote;
    private String status;
    private String instanceName;
    private List<Character> attendees;

    public RaidInstanceModel(String raidName, ZonedDateTime date, String instanceName, List<Character> attendees){
        this.raidName = raidName;
        this.date = date;
        this.instanceName = instanceName;
        this.attendees = attendees;
    }

 }

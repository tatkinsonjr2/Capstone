package sanctuaryraider.dynamodb.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import sanctuaryraider.dynamodb.models.Raid;
import sanctuaryraider.exceptions.RaidNotFoundException;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RaidDao {

    private final DynamoDBMapper dynamoDBMapper;

    @Inject
    public RaidDao(DynamoDBMapper dynamoDBMapper){
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public Raid getRaid(String raidName){
        Raid raid = dynamoDBMapper.load(Raid.class, raidName);
        if(raidName == null){
            throw new RaidNotFoundException();
        }
        return raid;
    }

    public Raid saveRaid(Raid raid){
        this.dynamoDBMapper.save(raid);
        return raid;
    }

    public void deleteRaid(Raid raid){
        this.dynamoDBMapper.delete(raid);
    }

    //This may need to be paginated at some point.
    public List<Raid> getAllRaids(){
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        return dynamoDBMapper.scan(Raid.class, scanExpression);
    }

    public List<Raid> getAllRaidsByCharacterName(String characterName){
        if(characterName == null){
        throw new IllegalArgumentException("passed in character name is null");
        }

        List<Raid> allRaids = getAllRaids();
        List<Raid> attendeeMatch = new ArrayList<>();
        for(Raid raid : allRaids){
           Set<String> attendees = raid.getAttendees();
           if(attendees != null) {
           for(String attendee : attendees){
               if(attendee != null && attendee.contains(characterName)){
                   attendeeMatch.add(raid);
               }
             }
           }
        }
        return attendeeMatch;
    }
}

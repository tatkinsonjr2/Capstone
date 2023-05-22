package sanctuaryraider.dynamodb.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.xspec.NULL;
import sanctuaryraider.dynamodb.models.Raid;
import sanctuaryraider.exceptions.RaidNotFoundException;

import javax.inject.Inject;
import java.util.List;

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
}

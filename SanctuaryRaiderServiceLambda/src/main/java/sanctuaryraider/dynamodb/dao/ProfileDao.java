package sanctuaryraider.dynamodb.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import sanctuaryraider.dynamodb.models.Profile;
import sanctuaryraider.exceptions.ProfileNotFoundException;

import javax.inject.Inject;
import java.util.List;

public class ProfileDao {
    private final DynamoDBMapper dynamoDBMapper;

    @Inject
    public ProfileDao(DynamoDBMapper dynamoDBMapper){
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public Profile getProfile(String username){
        Profile profile = dynamoDBMapper.load(Profile.class, username);
        if(username == null){
            throw new ProfileNotFoundException();
        }
        return profile;
    }

    public Profile saveProfile(Profile profile){
        this.dynamoDBMapper.save(profile);
        return profile;
    }

    public void deleteProfile(Profile profile){
        this.dynamoDBMapper.delete(profile);
    }

    public List<Profile> getAllProfiles(){
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        return dynamoDBMapper.scan(Profile.class, scanExpression);
    }
}

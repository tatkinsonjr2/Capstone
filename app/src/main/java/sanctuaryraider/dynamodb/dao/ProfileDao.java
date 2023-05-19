package sanctuaryraider.dynamodb.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import sanctuaryraider.dynamodb.models.Profile;

import javax.inject.Inject;

public class ProfileDao {
    private final DynamoDBMapper dynamoDBMapper;

    @Inject
    public ProfileDao(DynamoDBMapper dynamoDBMapper){
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public Profile getProfile(String username){
        Profile profile = dynamoDBMapper.load(Profile.class, username);
    }
}
